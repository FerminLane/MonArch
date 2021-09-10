package com.pomoguy.MonArch.controller.archcatalog;


import com.pomoguy.MonArch.dao.PlatformRepo;
import com.pomoguy.MonArch.dao.ProductRepo;
import com.pomoguy.MonArch.dao.VendorRepo;
import com.pomoguy.MonArch.model.User;
import com.pomoguy.MonArch.model.archcatalog.Product;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;


@Controller
@RequestMapping("/products")
@PreAuthorize("hasAuthority('ADMIN')")
public class ProductController {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private VendorRepo vendorRepo;

    @Autowired
    private PlatformRepo platformRepo;

    @PersistenceContext
    private EntityManager em;


    @GetMapping
    public String productGetList(Model model) {
        model.addAttribute("products", productRepo.findAll());
        return "archcatalog/products/productList";
    }

    @GetMapping("/add")
    public String productGetFormAdd(Model model) {
        model.addAttribute("vendors",vendorRepo.findByStatus("Active"));
        model.addAttribute("platforms",platformRepo.findByStatus("Active"));
        return "archcatalog/products/productAdd";
    }

    @PostMapping("/add")
    public String productAdd(@AuthenticationPrincipal User user,
                             @RequestParam String name,
                             @RequestParam String version,
                             @RequestParam String status,
                             @RequestParam String vendorId,
                             @RequestParam String platformId,
                             @RequestParam String description,
                             Model model) {

        Product product = new Product(name, version, user, description);

        product.setStatus(status);
        product.setVendor(vendorRepo.findById(vendorId).get());
        product.setPlatform(platformRepo.findById(platformId).get());
        product.setCreateDateTime();
        product.setUpdateDateTime();
        product.setUpdatedBy(user.getUsername());
        productRepo.save(product);
        return "redirect:/products/";
    }


    @GetMapping("{product}/profile")
    public String productGetProfile(@PathVariable Product product, Model model) {
        model.addAttribute("product", product);

        return "archcatalog/products/form/productProfile";
    }

    @PostMapping("{product}/profile/newversion")
    public String productNewVersion(@PathVariable Product product,
                                    @AuthenticationPrincipal User user,
                                    @RequestParam String version,
                                    Model model) {

        Product newProduct = new Product(product.getName(), version, user, product.getDescription());
        newProduct.setVendor(product.getVendor());
        newProduct.setPlatform(product.getPlatform());
        newProduct.setCreateDateTime();
        newProduct.setUpdateDateTime();
        newProduct.setUpdatedBy(user.getUsername());
        newProduct.setStatus("Actual");
        productRepo.save(newProduct);


        product.setUpdateDateTime();
        product.setUpdatedBy(user.getUsername());
        product.setStatus("Stale");
        productRepo.save(product);
        return "redirect:/products/" + newProduct.getId() + "/profile";
    }

    @GetMapping("{product}/profile/edit")
    public String productGetFormEdit(@PathVariable Product product, Model model) {
        model.addAttribute("vendors",vendorRepo.findByStatus("Active"));
        model.addAttribute("platforms",platformRepo.findByStatus("Active"));
        model.addAttribute("product", product);
        return "archcatalog/products/productEdit";
    }


    @PostMapping("{product}/profile/edit")
    public String productEdit(@PathVariable Product product,
                              @AuthenticationPrincipal User user,
                              @RequestParam String name,
                              @RequestParam String description,
                              @RequestParam String vendorId,
                              @RequestParam String platformId,
                              Model model) {

        product.setName(name);
        product.setDescription(description);
        product.setVendor(vendorRepo.findById(vendorId).get());
        product.setPlatform(platformRepo.findById(platformId).get());
        product.setUpdateDateTime();
        product.setUpdatedBy(user.getUsername());

        productRepo.save(product);
        return "redirect:/products/" + product.getId() + "/profile";
    }


    @GetMapping("{product}/history")
    public String productGetHistory(@PathVariable Product product, Model model) {
        AuditQuery query = AuditReaderFactory.get(em)
                .createQuery()
                .forRevisionsOfEntity(Product.class, false, false)
                .add(AuditEntity.property("id").eq(product.getId()));
        List<Object[]> audit = query.getResultList();
        Collections.reverse(audit);
        model.addAttribute("product", product);
        model.addAttribute("audit", audit);
        return "archcatalog/products/form/productHistory";
    }

    @GetMapping("{product}/history/{rev}/profile")
    public String historyProductGetProfile(@PathVariable Product product, @PathVariable Integer rev, Model model) {
        AuditQuery query = AuditReaderFactory.get(em)
                .createQuery()
                .forEntitiesAtRevision(Product.class, rev)
                .add(AuditEntity.property("id").eq(product.getId()));
        ;
        product = (Product) query.getSingleResult();
        model.addAttribute("isHistoryObj", true);
        model.addAttribute("product", product);
        return "archcatalog/products/form/productProfile";
    }


    @GetMapping("{product}/docs")
    public String productGetDocs(@PathVariable Product product, Model model) {
        List<Object> docs = null;
        model.addAttribute("docs", docs);
        model.addAttribute("product", product);
        return "archcatalog/products/form/productDocs";
    }

}