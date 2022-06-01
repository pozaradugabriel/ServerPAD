package com.artexplorer.proiectpad.controller;

import com.artexplorer.proiectpad.service.MuseumService;
import com.artexplorer.proiectpad.service.UserService;
import com.artexplorer.proiectpad.model.Rating;
import com.artexplorer.proiectpad.model.Museum;
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

    @GetMapping(path = "/wishlist/get")
    public ResponseEntity<List<Museum>> getUserWishlist()
    {
        // Get the logged in user
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        List<Museum> wishlist = userService.findWishlistByUsername(username);

        return new ResponseEntity<>(wishlist, HttpStatus.OK);
    }

    @GetMapping(path = "/visited/get")
    public ResponseEntity<List<Museum>> getUserVisited()
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        List<Museum> visitedlist = userService.findVisitedByUsername(username);

        return new ResponseEntity<>(visitedlist, HttpStatus.OK);
    }

    @PostMapping(path = "/wishlist/add/{museumId}")
    public ResponseEntity<?> addToWishlist(@PathVariable("museumId") Long museumId)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        userService.addMuseumToWishlist(username, museumId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/visited/add/{museumId}")
    public ResponseEntity<?> addToVisited(@PathVariable("museumId") Long museumId)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        userService.addMuseumToVisited(username, museumId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/wishlist/del/{museumId}")
    public ResponseEntity<?> delFromWishlist(@PathVariable("museumId") Long museumId)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        userService.removeMuseumFromWishlist(username, museumId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/visited/del/{museumId}")
    public ResponseEntity<?> delFromVisited(@PathVariable("museumId") Long museumId)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        userService.removeMuseumFromVisited(username, museumId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}