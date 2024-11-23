package be.ecotravel.repository;

import be.ecotravel.entity.Step;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StepRepository extends JpaRepository<Step, UUID> {
}
