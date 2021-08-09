package com.pomoguy.MonArch.dao;

import com.pomoguy.MonArch.model.committee.Committee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommitteeRepo extends JpaRepository<Committee, Long> {
    Committee findByAgenda(String agenda);
}
