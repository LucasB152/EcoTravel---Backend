package be.ecotravel.back.repository;

import be.ecotravel.back.entity.DestinationType;
import be.ecotravel.back.entity.DestinationTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationTypeRepository extends JpaRepository<DestinationType, Integer> {

    boolean existsByType(DestinationTypeEnum name);

}
