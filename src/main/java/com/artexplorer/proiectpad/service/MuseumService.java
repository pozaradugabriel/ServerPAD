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

        Museum museum1 = new Museum(45.7773880667123, 21.26587509611117, "Muzeul Satului Banatean", "Open-air architecture museum documenting village life with traditional homes, a school and a church","Folkloric");
        Museum museum2 = new Museum(45.75756070312408, 21.229249882618166,"Muzeul de Arta Timisoara","Large museum housed in an elegant baroque palace, featuring classic & contemporary artwork.","Art");
        Museum museum3 = new Museum(45.75692951816426, 21.232642299838727,"National Museum of Banat", "19th-century museum in a former castle featuring historical & cultural exhibits of local interest.","History");
        Museum museum4 = new Museum(45.74341228871662, 21.224212582617653,"Communist Consumer Museum", "Communist-themed museum housed in the basement of a vintage coffee place.","History");
        Museum museum5 = new Museum(45.76759666653226, 21.240003940306174,"Tools Museum"," ","History");
        museumRepository.save(museum1);
        museumRepository.save(museum2);
        museumRepository.save(museum3);
        museumRepository.save(museum4);
        museumRepository.save(museum5);
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
