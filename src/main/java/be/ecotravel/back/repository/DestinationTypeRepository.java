package be.ecotravel.back.repository;

import be.ecotravel.back.entity.DestinationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationTypeRepository extends JpaRepository<DestinationType, Integer> {

    boolean existsByName(String name);

}
