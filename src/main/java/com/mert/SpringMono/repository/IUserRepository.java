package com.mert.SpringMono.repository;

import com.mert.SpringMono.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User,Long> {

    Boolean existsUserByUsername(String username);
}
