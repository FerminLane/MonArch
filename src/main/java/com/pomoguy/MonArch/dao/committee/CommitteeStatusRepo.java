package com.pomoguy.MonArch.dao.committee;



import com.pomoguy.MonArch.model.committee.CommitteeStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommitteeStatusRepo extends JpaRepository<CommitteeStatus, String> {
    List<CommitteeStatus> findByIsAvailable(Boolean isAvailable);
}
