package com.example.demo.repository;

import com.example.demo.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {

     @Query("SELECT r FROM Result r WHERE r.query.id = ?1")
     List<Result> findByQueryId(Long queryId);
}
