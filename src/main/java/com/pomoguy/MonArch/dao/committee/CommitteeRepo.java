package com.pomoguy.MonArch.dao.committee;

import com.pomoguy.MonArch.model.committee.Committee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommitteeRepo extends JpaRepository<Committee, String> {
    Committee findByAgenda(String agenda);
}
