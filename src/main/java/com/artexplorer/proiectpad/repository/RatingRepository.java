package com.artexplorer.proiectpad.repository;

import com.artexplorer.proiectpad.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface RatingRepository extends JpaRepository<Rating, Long> {

    Optional<Rating> findRatingByUserIdAndMuseumId(Long userId, Long museumId);

    List<Rating> findRatingsByMuseumId(Long museumId);
}