package com.artexplorer.proiectpad.service;

import com.artexplorer.proiectpad.exception.MuseumNotFoundException;
import com.artexplorer.proiectpad.model.Museum;
import com.artexplorer.proiectpad.repository.MuseumRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MuseumService {
    private MuseumRepository museumRepository;

    public MuseumService(MuseumRepository museumRepository){
        this.museumRepository=museumRepository;
    }

    public List<Museum> getMuseums(){
        return museumRepository.findAll();
    }

    public List<Museum> findAllMuseums() {
        return museumRepository.findAll();
    }

    public Museum findMuseum(Long museumId) {
        return museumRepository.findById(museumId)
                .orElseThrow(() -> new MuseumNotFoundException(String.format("Museum with the id %d not found!", museumId)));
    }
}
