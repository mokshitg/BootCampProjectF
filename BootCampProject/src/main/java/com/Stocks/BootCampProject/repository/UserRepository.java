package com.Stocks.BootCampProject.repository;

import com.Stocks.BootCampProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
