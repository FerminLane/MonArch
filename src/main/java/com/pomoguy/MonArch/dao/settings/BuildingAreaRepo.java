package com.pomoguy.MonArch.dao.settings;



import com.pomoguy.MonArch.model.archcatalog.Platform;
import com.pomoguy.MonArch.model.settings.BuildingArea;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BuildingAreaRepo extends CrudRepository<BuildingArea, String> {
    List<BuildingArea> findByIsActual(Boolean isActual);
}
