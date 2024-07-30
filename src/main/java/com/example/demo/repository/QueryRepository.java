package com.example.demo.repository;

import com.example.demo.entity.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QueryRepository extends JpaRepository<Query, Long> {

    @org.springframework.data.jpa.repository.Query("SELECT q FROM Query q WHERE q.latitude = ?1 AND q.longitude = ?2 AND q.radius = ?3")
    Optional<Query> findByLatitudeAndLongitudeAndRadius(double latitude, double longitude, double radius);
}
