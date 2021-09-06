package com.pomoguy.MonArch.dao;

import com.pomoguy.MonArch.model.archcatalog.ITSystem;
import com.pomoguy.MonArch.model.archcatalog.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends CrudRepository<Product, Long> {

}
