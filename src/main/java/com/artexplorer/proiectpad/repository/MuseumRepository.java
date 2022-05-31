package com.artexplorer.proiectpad.repository;

import com.artexplorer.proiectpad.model.Museum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface MuseumRepository extends JpaRepository<Museum,Long> {
    Optional<Museum> findById(Long id);
}
