package be.ecotravel.back.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class GoogleMapService {

    private final String apiKey;
    private final String destinationUrl = "https://maps.googleapis.com/maps/api/directions/json";

    public GoogleMapService(@Value("${google.api.key}") String apiKey) {
        this.apiKey = apiKey;
    }

    public double fetchDistanceFromAPI(String origin, String destination) {
        WebClient client = WebClient.create();



        return 0; //TODO
    }

    public double fetchDistanceFromAPI(String origin, List<String> steps, String destination) {
        return 0; //TODO
    }

}
