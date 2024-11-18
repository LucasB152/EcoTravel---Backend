package be.ecotravel.ecotravelback.repository;

import be.ecotravel.ecotravelback.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
}
