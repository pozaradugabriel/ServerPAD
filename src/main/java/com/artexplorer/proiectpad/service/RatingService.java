package com.artexplorer.proiectpad.service;

import com.artexplorer.proiectpad.model.Museum;
import com.artexplorer.proiectpad.model.Rating;
import com.artexplorer.proiectpad.model.RatingRequest;
import com.artexplorer.proiectpad.model.User;
import com.artexplorer.proiectpad.repository.RatingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
@AllArgsConstructor
public class RatingService implements Serializable {

    private final RatingRepository ratingRepository;
    private final MuseumService museumService;
    private final UserService userService;

    public Rating getRating(String username, Long museumId) {

        User user = userService.findUserByUsername(username);
        if(ratingRepository.findRatingByUserIdAndMuseumId(user.getId(), museumId).isPresent())
            return ratingRepository.findRatingByUserIdAndMuseumId(user.getId(), museumId).get();
        else return null;
    }

    public Float getTotalMuseumRating(Long museumId) {
        List<Rating> ratings = ratingRepository.findRatingsByMuseumId(museumId);
        Float totalGrade = 0F;
        if(ratings.stream().count() != 0)
        {
            for (Rating rating: ratings) {
                totalGrade += rating.getGrade();
            }

            totalGrade /= ratings.stream().count();
        }

        return totalGrade;
    }

    public void addRating(String username, RatingRequest ratingRequest)
    {
        User user = userService.findUserByUsername(username);
        Museum museum = museumService.findMuseum(ratingRequest.getMuseumId());

        if(ratingRepository.findRatingByUserIdAndMuseumId(user.getId(), museum.getId()).isPresent())
        {
            Rating rating = ratingRepository.findRatingByUserIdAndMuseumId(user.getId(), museum.getId()).get();
            rating.setGrade(ratingRequest.getGrade());
            ratingRepository.save(rating);
        }
        else
        {
            Rating r = new Rating(
                    ratingRequest.getGrade(),
                    ratingRequest.getMuseumId(),
                    user.getUserInfo());



            ratingRepository.save(
                    r
            );
        }
    }

    public void deleteRating(String username, Long museumId)
    {
        User user = userService.findUserByUsername(username);

        Rating rating = ratingRepository.findRatingByUserIdAndMuseumId(user.getId(), museumId).get();

        ratingRepository.delete(rating);
    }
}