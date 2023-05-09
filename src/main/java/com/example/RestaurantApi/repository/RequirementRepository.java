package com.example.RestaurantApi.repository;

import com.example.RestaurantApi.model.entity.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequirementRepository extends JpaRepository<Requirement, Integer> {
}
