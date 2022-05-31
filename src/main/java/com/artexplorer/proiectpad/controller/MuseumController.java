package com.artexplorer.proiectpad.controller;

import com.artexplorer.proiectpad.model.Museum;
import com.artexplorer.proiectpad.service.MuseumService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="museums")
public class MuseumController {
    private final MuseumService museumService;

    public MuseumController(MuseumService museumService) {
        this.museumService = museumService;
    }

    @GetMapping
    public List<Museum> getAllMuseums(){return museumService.getMuseums();}
}
