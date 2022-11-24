package com.example.RestaurantApi.repository;

import com.example.RestaurantApi.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
