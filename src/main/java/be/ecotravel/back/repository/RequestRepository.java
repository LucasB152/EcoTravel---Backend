package be.ecotravel.back.repository;

import be.ecotravel.back.entity.Request;
import be.ecotravel.back.entity.RequestStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RequestRepository extends JpaRepository<Request, UUID> {
    List<Request> findByRequestStatus(RequestStatusEnum requestStatusEnum);
}
