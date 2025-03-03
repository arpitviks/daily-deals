package com.dailyDeals.dailyDeals_v6.repositories.interfaces;

import com.dailyDeals.dailyDeals_v6.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);
}
