package com.example.demo.controller;

import com.example.demo.entity.Place;
import com.example.demo.entity.Query;
import com.example.demo.service.PlaceService;

import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/")
public class PlaceController {




    private final PlaceService placeService;



    @GetMapping
    public List<Place> getNearbyPlaces(@RequestParam double latitude, @RequestParam double longitude, @RequestParam double radius) {
        List<Place> places = placeService.getPlaces(latitude, longitude, radius);
        if (places.isEmpty()) {
            String urlTemplate = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=%f,%f&radius=%f&key=%s";

            String url = String.format(urlTemplate, latitude, longitude, radius, "secret");

            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(url, String.class);

            JSONObject jsonResponse = new JSONObject(response);
            JSONArray results = jsonResponse.getJSONArray("results");

            for (int i = 0; i < results.length(); i++) {
                JSONObject placeJson = results.getJSONObject(i);
                Place place = new Place();
                place.setName(placeJson.getString("name"));
                place.setVicinity(placeJson.getString("vicinity"));
                place.setLatitude(placeJson.getJSONObject("geometry").getJSONObject("location").getDouble("lat"));
                place.setLongitude(placeJson.getJSONObject("geometry").getJSONObject("location").getDouble("lng"));
                places.add(place);
            }

            Query query = new Query();
            query.setLatitude(latitude);
            query.setLongitude(longitude);
            query.setRadius(radius);
            placeService.saveQueryResults(query, places);
        }
        return places;
    }
}
