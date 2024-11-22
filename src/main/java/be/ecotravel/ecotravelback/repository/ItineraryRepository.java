package be.ecotravel.ecotravelback.repository;

import be.ecotravel.ecotravelback.entity.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItineraryRepository extends JpaRepository<Itinerary, UUID> {
}
