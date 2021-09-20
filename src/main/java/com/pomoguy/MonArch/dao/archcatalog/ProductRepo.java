package com.pomoguy.MonArch.dao.archcatalog;


import com.pomoguy.MonArch.model.archcatalog.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends CrudRepository<Product, String> {

}
