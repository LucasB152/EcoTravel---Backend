package be.ecotravel.back.repository;

import be.ecotravel.back.entity.Destination;
import be.ecotravel.back.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DestinationRepository extends JpaRepository<Destination, UUID> {
    List<Destination> findAllByUser(User user);
}
