package com.pomoguy.MonArch.dao.archcatalog;



import com.pomoguy.MonArch.model.archcatalog.BuildingArea;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BuildingAreaRepo extends CrudRepository<BuildingArea, String> {
    List<BuildingArea> findByIsActual(Boolean isActual);
}
