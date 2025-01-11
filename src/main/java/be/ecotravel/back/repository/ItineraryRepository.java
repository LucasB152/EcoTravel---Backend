package be.ecotravel.back.repository;

import be.ecotravel.back.entity.Itinerary;
import be.ecotravel.back.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ItineraryRepository extends JpaRepository<Itinerary, UUID> {
    List<Itinerary> findByOwnerUser(User ownerUser);
}
