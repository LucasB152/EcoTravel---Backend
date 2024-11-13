package be.ecotravel.ecotravelback.service;

import be.ecotravel.ecotravelback.entity.Destination;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DestinationService {

    public ResponseEntity<List<Destination>> getPopular(int number) {
        List<Destination> destinations = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            Destination destination = new Destination(String.format("Name %d", i), String.format("Description %d", i));
            destinations.add(destination);
        }

        return new ResponseEntity<>(destinations, HttpStatus.OK);
    }
}
