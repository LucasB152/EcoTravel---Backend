package be.ecotravel.back.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GoogleDestinationService {

    @Value("${google.api.key}")
    private String apiKey;

    public double fetchDistanceFromAPI(String originCoordinates, List<String> stepsCoordinates, String destinationCoordinates) {
        String stepParameters = "";

        if (!stepsCoordinates.isEmpty()) {
            stepParameters = "&waypoints=";
            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < stepsCoordinates.size() - 1; i++) {
                builder.append("via:").append(stepsCoordinates.get(i)).append("|");
            }
            builder.append("via:").append(stepsCoordinates.getLast());
            stepParameters += builder.toString();
        }

        String parameters = "destination=" + destinationCoordinates + "&origin=" + originCoordinates + stepParameters;

        String baseUrl = "https://maps.googleapis.com/maps/api/directions/json?%s&key=%s";
        String finalUrl = String.format(baseUrl, parameters, apiKey);

        System.out.println(finalUrl);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(finalUrl, String.class);
        return getDistanceFromResponse(response.getBody());
    }

    private double getDistanceFromResponse(String response) {
        JsonArray legs = getLegsFromResponse(response);
        return legs.get(0).getAsJsonObject().get("distance").getAsJsonObject().get("value").getAsDouble();
    }

    private JsonArray getLegsFromResponse(String response) {
        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
        JsonArray routes = jsonObject.get("routes").getAsJsonArray();
        return routes.get(0).getAsJsonObject().get("legs").getAsJsonArray();
    }

}
