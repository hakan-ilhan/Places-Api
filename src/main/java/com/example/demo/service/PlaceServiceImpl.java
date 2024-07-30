package com.example.demo.service;

import com.example.demo.entity.Place;
import com.example.demo.entity.Query;
import com.example.demo.entity.Result;
import com.example.demo.repository.PlaceRepository;
import com.example.demo.repository.QueryRepository;
import com.example.demo.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlaceServiceImpl implements PlaceService{

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private QueryRepository queryRepository;

    @Autowired
    private ResultRepository resultRepository;

    public List<Place> getPlaces(double latitude, double longitude, double radius) {
        Optional<Query> existingQuery = queryRepository.findByLatitudeAndLongitudeAndRadius(latitude, longitude, radius);

        if (existingQuery.isPresent()) {
            List<Result> queryResults = resultRepository.findByQueryId(existingQuery.get().getId());
            List<Place> places = new ArrayList<>();
            for (Result result : queryResults) {
                places.add(result.getPlace());
            }
            return places;
        } else {
            return new ArrayList<>();
        }
    }

    public void saveQueryResults(Query query, List<Place> places) {
        query = queryRepository.save(query);
        for (Place place : places) {
            place = placeRepository.save(place);
            Result queryResult = new Result();
            queryResult.setQuery(query);
            queryResult.setPlace(place);
            resultRepository.save(queryResult);
        }
    }

}


