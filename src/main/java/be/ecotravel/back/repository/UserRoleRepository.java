package be.ecotravel.back.repository;

import be.ecotravel.back.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

    // Méthode pour rechercher un rôle par son nom
    Optional<UserRole> findByName(String name);

    boolean existsByName(String name);
}
