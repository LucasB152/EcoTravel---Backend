package be.ecotravel.ecotravelback.repository;

import be.ecotravel.ecotravelback.entity.Step;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StepRepository extends JpaRepository<Step, UUID> {
}
