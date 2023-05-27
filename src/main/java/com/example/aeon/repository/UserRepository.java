package com.example.aeon.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aeon.models.Users;


@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

  Users findByEmail(String email);

}
