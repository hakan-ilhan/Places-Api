package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "place", schema = "public")
public class Place {
    @Id
    @Column(length = 255)
    private Long id;

    private String name;

    private String vicinity;

    private double latitude;

    private double longitude;

    @OneToMany(mappedBy = "place")
    private List<Result> queryResults;


}
