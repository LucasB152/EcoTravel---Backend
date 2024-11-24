package be.ecotravel.repository;

import be.ecotravel.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface DestinationRepository extends JpaRepository<Destination, UUID> {

}