package com.example.demo.service;

import com.example.demo.entity.Place;
import com.example.demo.entity.Query;

import java.util.List;

public interface PlaceService {
    List<Place> getPlaces(double latitude, double longitude, double radius);
    void saveQueryResults(Query query, List<Place> places);
}
