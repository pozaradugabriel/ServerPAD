package com.artexplorer.proiectpad.controller;

import com.artexplorer.proiectpad.service.MuseumService;
import com.artexplorer.proiectpad.service.UserService;
import com.artexplorer.proiectpad.model.Rating;
import com.artexplorer.proiectpad.model.RatingRequest;
import com.artexplorer.proiectpad.service.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "api/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final RatingService ratingService;
    private final MuseumService museumService;

    @GetMapping(path = "/rating/get/{museumId}")
    public ResponseEntity<Integer> getRating(@PathVariable("museumId") Long museumId)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Rating rating = ratingService.getRating(username, museumId);

        return new ResponseEntity<>(rating != null ? rating.getGrade() : 0, HttpStatus.OK);
    }

    @PostMapping(path = "/rating/add")
    public ResponseEntity<?> addRating(@RequestBody RatingRequest ratingRequest)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        ratingService.addRating(username, ratingRequest);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/rating/del/{museumId}")
    public ResponseEntity<?> delRating(@PathVariable("museumId") Long museumId)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        ratingService.deleteRating(username, museumId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}