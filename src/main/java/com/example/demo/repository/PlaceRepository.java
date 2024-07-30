package com.example.demo.repository;

import com.example.demo.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    @Query("SELECT p FROM Place p WHERE p.name = ?1")
    Place findByName(String name);
}
