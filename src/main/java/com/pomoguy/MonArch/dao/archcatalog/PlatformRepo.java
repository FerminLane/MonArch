package com.pomoguy.MonArch.dao.archcatalog;


import com.pomoguy.MonArch.model.archcatalog.Platform;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlatformRepo extends CrudRepository<Platform, String> {
    List<Platform> findByIsActual(Boolean isActual);
}
