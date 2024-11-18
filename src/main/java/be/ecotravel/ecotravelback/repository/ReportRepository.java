package be.ecotravel.ecotravelback.repository;

import be.ecotravel.ecotravelback.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReportRepository extends JpaRepository<Report, UUID> {
}
