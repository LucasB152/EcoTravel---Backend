package be.ecotravel.back.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private UserRoleEnum name;

    protected UserRole() {}

    public UserRole(UserRoleEnum name) {
        this.name = name;
    }

}
