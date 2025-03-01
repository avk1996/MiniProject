package com.app.rms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.rms.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
