package be.ecotravel.ecotravelback.repository;

import be.ecotravel.ecotravelback.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
}
