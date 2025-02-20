package com.Gestion.Gestapp.repository;

import com.Gestion.Gestapp.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MyUserRepository extends JpaRepository<MyUser, Long> {

    Optional<MyUser>findByUsername(String username);
}
