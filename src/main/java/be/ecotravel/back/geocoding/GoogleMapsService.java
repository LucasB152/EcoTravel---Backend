package be.ecotravel.back.geocoding;

import be.ecotravel.back.address.dto.AddressCreationDto;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import org.springframework.stereotype.Service;

@Service
public class GoogleMapsService {

    private final GeoApiContext context;

    public GoogleMapsService(GeoApiContext context) {
        this.context = context;
    }

    public GeocodingResult[] geocodeAddress(String address) throws Exception {
        return GeocodingApi.geocode(context, address).await();
    }

    public AddressCreationDto getAddressCreationDto(GeocodingResult geocodingResult) {
        String country = "";
        String location = "";
        String street = "";
        String number = "";
        String postalCode = "";
        double longitude;
        double latitude;
        for (int i = 0; i < geocodingResult.addressComponents.length; i++) {
            String type = geocodingResult.addressComponents[i].types[0].toString();
            String value = geocodingResult.addressComponents[i].longName;
            switch (type) {
                case "country" -> country = value;
                case "locality" -> location = value;
                case "route" -> street = value;
                case "street_number" -> number = value;
                case "postal_code" -> postalCode = value;
            }
        }
        longitude = geocodingResult.geometry.location.lng;
        latitude = geocodingResult.geometry.location.lat;
        return new AddressCreationDto(country, location, street, number, postalCode, longitude, latitude);
    }
}
