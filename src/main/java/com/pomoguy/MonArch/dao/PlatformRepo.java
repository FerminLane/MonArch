package com.pomoguy.MonArch.dao;


import com.pomoguy.MonArch.model.archcatalog.Platform;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlatformRepo extends CrudRepository<Platform, String> {
    List<Platform> findByStatus(String status);
}
