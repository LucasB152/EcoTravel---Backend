package be.ecotravel.repository;

import be.ecotravel.entity.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItineraryRepository extends JpaRepository<Itinerary, UUID> {
}
