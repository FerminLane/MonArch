package com.pomoguy.MonArch.dao.archcatalog;


import com.pomoguy.MonArch.model.archcatalog.Vendor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VendorRepo extends CrudRepository<Vendor, String> {
    List<Vendor> findByIsActual(Boolean isActual);
}
