package com.artexplorer.proiectpad.controller;

import com.artexplorer.proiectpad.model.Museum;
import com.artexplorer.proiectpad.service.MuseumService;
import com.artexplorer.proiectpad.service.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path="api/museum")
public class MuseumController {

    private final MuseumService museumService;
    private final RatingService ratingService;

    @GetMapping(path = "/get/all")
    public ResponseEntity<List<Museum>> getAllMuseums() {

        List<Museum> museums = museumService.getMuseums();
        return new ResponseEntity<>(museums, HttpStatus.OK);
    }

    @GetMapping(path = "/get/{museumId}")
    public ResponseEntity<Museum> getMuseum(@PathVariable("museumId") Long museumId) {
        Museum museum = museumService.findMuseum(museumId);
        return new ResponseEntity<>(museum, HttpStatus.OK);
    }

    @GetMapping(path = "/rating/get/{museumId}")
    public ResponseEntity<Float> getMuseumRating(@PathVariable("museumId") Long museumId) {
        float rating = ratingService.getTotalMuseumRating(museumId);
        return new ResponseEntity<>(rating, HttpStatus.OK);
    }
}
