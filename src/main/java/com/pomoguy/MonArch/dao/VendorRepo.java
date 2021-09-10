package com.pomoguy.MonArch.dao;


import com.pomoguy.MonArch.model.archcatalog.Vendor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VendorRepo extends CrudRepository<Vendor, String> {
    List<Vendor> findByStatus(String status);
}
