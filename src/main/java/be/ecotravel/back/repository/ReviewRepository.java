package be.ecotravel.back.repository;

import be.ecotravel.back.entity.Destination;
import be.ecotravel.back.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {

    List<Review> findByDestination(Destination destination);

}
