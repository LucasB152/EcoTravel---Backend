package be.ecotravel.ecotravelback.repository;

import be.ecotravel.ecotravelback.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface DestinationRepository extends JpaRepository<Destination, UUID> {

}
