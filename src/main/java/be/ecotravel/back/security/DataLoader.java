package be.ecotravel.back.security;

import be.ecotravel.back.entity.UserRole;
import be.ecotravel.back.repository.UserRoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRoleRepository userRoleRepository;

    public DataLoader(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRoleRepository.count() == 0) {
            userRoleRepository.save(new UserRole(UUID.randomUUID(), "USER"));
            userRoleRepository.save(new UserRole(UUID.randomUUID(), "ADMIN"));
            userRoleRepository.save(new UserRole(UUID.randomUUID(), "HEBERGEUR"));
        }
    }
}
