package be.ecotravel.back.service;

import be.ecotravel.back.address.mapper.AddressMapper;
import be.ecotravel.back.entity.Address;
import be.ecotravel.back.geocoding.GoogleMapsService;
import be.ecotravel.back.repository.AddressRepository;
import com.google.maps.model.GeocodingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    private final GoogleMapsService googleMapsService;
    private final AddressMapper addressMapper;
    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(GoogleMapsService googleMapsService, AddressMapper addressMapper, AddressRepository addressRepository) {
        this.googleMapsService = googleMapsService;
        this.addressMapper = addressMapper;
        this.addressRepository = addressRepository;;
    }

    public Address createAddress(String country, String location, String street, String number, String zipCode){
        String addressString = street + " " + number + " " + location;
        double latitude = 0.;
        double longitude = 0.;
        try {
            GeocodingResult[] geocodingResult = googleMapsService.geocodeAddress(addressString);
            latitude = geocodingResult[0].geometry.location.lat;
            longitude = geocodingResult[0].geometry.location.lng;
        } catch (Exception e) {
            System.out.println("erreur map" + e);
        }
        Address address = addressMapper.toEntity(country, location, street, number, zipCode, longitude, latitude);

        addressRepository.save(address);

        return address;
    }
}
