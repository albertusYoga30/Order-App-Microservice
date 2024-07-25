package com.maltesepu.repository;

import com.maltesepu.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepo extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByName(String name);
}
