package be.ecotravel.back.repository;

import be.ecotravel.back.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface UserRoleRepository extends JpaRepository<UserRole, UUID> {

    // Méthode pour rechercher un rôle par son nom
    Optional<UserRole> findByName(String name);
}
