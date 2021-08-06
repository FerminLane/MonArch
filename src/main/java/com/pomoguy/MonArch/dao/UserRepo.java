package com.pomoguy.MonArch.dao;

import com.pomoguy.MonArch.model.Message;
import com.pomoguy.MonArch.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
