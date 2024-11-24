package be.ecotravel.back.repository;

import be.ecotravel.back.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface DestinationRepository extends JpaRepository<Destination, UUID> {

}
