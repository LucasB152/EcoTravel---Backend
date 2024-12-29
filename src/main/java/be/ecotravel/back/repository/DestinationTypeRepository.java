package be.ecotravel.back.repository;

import be.ecotravel.back.entity.DestinationType;
import be.ecotravel.back.entity.DestinationTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DestinationTypeRepository extends JpaRepository<DestinationType, Integer> {

    boolean existsByType(DestinationTypeEnum name);

    @Query("SELECT type FROM DestinationType ")
    List<String> getAllTypes();

}
