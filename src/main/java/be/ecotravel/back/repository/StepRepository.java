package be.ecotravel.back.repository;

import be.ecotravel.back.entity.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface StepRepository extends JpaRepository<Step, UUID> {

    @Query("SELECT MAX(s.orderSequence) FROM Step s WHERE s.itinerary.id = :itineraryId")
    Integer findMaxOrderSequenceByItineraryId(@Param("itineraryId") UUID itineraryId);

    List<Step> findByItineraryIdOrderByOrderSequenceAsc(UUID itineraryId);

    @Query("SELECT s FROM Step s WHERE s.itinerary.id = :itineraryId AND s.orderSequence = :orderSequence")
    Step findByItineraryIdAndOrderSequence(@Param("itineraryId") UUID itineraryId, @Param("orderSequence") int orderSequence);

}
