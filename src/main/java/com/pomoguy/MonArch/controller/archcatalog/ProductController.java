package com.pomoguy.MonArch.controller.archcatalog;


import com.pomoguy.MonArch.controller.ControllerUtils;
import com.pomoguy.MonArch.dao.archcatalog.PlatformRepo;
import com.pomoguy.MonArch.dao.archcatalog.ProductRepo;
import com.pomoguy.MonArch.dao.archcatalog.VendorRepo;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;


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
        model.addAttribute("vendors", vendorRepo.findByIsActual(true));
        model.addAttribute("platforms", platformRepo.findByIsActual(true));
        return "archcatalog/products/productAdd";
    }

    @PostMapping("/add")
    public String productAdd(@AuthenticationPrincipal User user,
                            @Valid Product product,
                             BindingResult bindingResult,
                             Model model) {

        if (bindingResult.hasErrors()) {

            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return productGetFormAdd(model);
        }
        product.setAuthor(user);
        //product.setVendor(vendorRepo.findById(vendorId).get());
        //product.setPlatform(platformRepo.findById(platformId).get());
        //product.setDescription(description);
        product.setCreateDateTime();
        product.setUpdateDateTime();
        product.setActual(true);
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
                                    @RequestParam Product newProduct,
                                    @AuthenticationPrincipal User user,
                                    Model model) {

        newProduct.setAuthor(user);
        newProduct.setVendor(product.getVendor());
        newProduct.setPlatform(product.getPlatform());
        newProduct.setCreateDateTime();
        newProduct.setUpdateDateTime();
        newProduct.setUpdatedBy(user.getUsername());
        newProduct.setActual(true);
        productRepo.save(newProduct);


        product.setUpdateDateTime();
        product.setUpdatedBy(user.getUsername());
        product.setActual(false);
        productRepo.save(product);
        return "redirect:/products/" + newProduct.getId() + "/profile";
    }

    @GetMapping("{product}/profile/edit")
    public String productGetFormEdit(@PathVariable Product product, Model model) {
        model.addAttribute("vendors", vendorRepo.findByIsActual(true));
        model.addAttribute("platforms", platformRepo.findByIsActual(true));
        model.addAttribute("product", product);
        return "archcatalog/products/productEdit";
    }


    @PostMapping("{product}/profile/edit")
    public String productEdit(@PathVariable Product product,
                              @AuthenticationPrincipal User user,
                              @RequestParam Product editedProduct,
                              Model model) {

        product.setName(editedProduct.getName());
        product.setDescription(editedProduct.getDescription());
        product.setVendor(editedProduct.getVendor());
        product.setPlatform(editedProduct.getPlatform());
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