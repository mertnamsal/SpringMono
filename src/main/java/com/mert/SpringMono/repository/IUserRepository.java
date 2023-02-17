package com.mert.SpringMono.repository;

import com.mert.SpringMono.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User,Long> {

    Boolean existsUserByUsername(String username);

    Optional<User> findOptionalByUsernameAndPassword(String username,String password);
}
