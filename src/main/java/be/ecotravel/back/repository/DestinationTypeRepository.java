package be.ecotravel.back.repository;

import be.ecotravel.back.entity.DestinationType;
import be.ecotravel.back.entity.DestinationTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DestinationTypeRepository extends JpaRepository<DestinationType, Integer> {
    Optional<DestinationType> findByType(DestinationTypeEnum type);

    boolean existsByType(DestinationTypeEnum name);

    @Query("SELECT type FROM DestinationType ")
    List<String> getAllTypes();

}
