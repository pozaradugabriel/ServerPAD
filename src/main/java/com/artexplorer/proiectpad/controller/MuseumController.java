package com.artexplorer.proiectpad.controller;

import com.artexplorer.proiectpad.model.Museum;
import com.artexplorer.proiectpad.service.MuseumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="api/museum")
public class MuseumController {

    private final MuseumService museumService;

    public MuseumController(MuseumService museumService) {
        this.museumService = museumService;
    }

    @GetMapping(path = "/get/all")
    public ResponseEntity<List<Museum>> getAllMuseums() {

        List<Museum> museums = museumService.getMuseums();
        return new ResponseEntity<>(museums, HttpStatus.OK);
    }
}
