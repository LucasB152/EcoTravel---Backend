package be.ecotravel.back;

import be.ecotravel.back.entity.DestinationType;
import be.ecotravel.back.entity.DestinationTypeEnum;
import be.ecotravel.back.entity.UserRole;
import be.ecotravel.back.entity.UserRoleEnum;
import be.ecotravel.back.repository.DestinationTypeRepository;
import be.ecotravel.back.repository.UserRoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRoleRepository userRoleRepository;

    private final DestinationTypeRepository destinationRepo;

    public DataLoader(UserRoleRepository userRoleRepository, DestinationTypeRepository destinationRepo) {
        this.userRoleRepository = userRoleRepository;
        this.destinationRepo = destinationRepo;
    }

    @Override
    public void run(String... args) {
        seedUserRole();
        seedDestinationType();
    }

    private void seedUserRole() {
        UserRoleEnum[] roleValues = UserRoleEnum.values();

        if (userRoleRepository.count() != roleValues.length) {
            for (UserRoleEnum roleValue : roleValues) {
                if (userRoleRepository.existsByName(roleValue)) {
                    continue;
                }

                userRoleRepository.save(new UserRole(roleValue));
            }
        }
    }

    private void seedDestinationType() {
        DestinationTypeEnum[] typeValues = DestinationTypeEnum.values();

        if (destinationRepo.count() != typeValues.length) {
            for (DestinationTypeEnum destinationType : typeValues) {
                if (destinationRepo.existsByType(destinationType)) {
                    continue;
                }

                destinationRepo.save(new DestinationType(destinationType));
            }
        }
    }
}
