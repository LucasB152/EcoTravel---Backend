package be.ecotravel.back.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String name;

    protected UserRole() {}

    public UserRole(String name) {
        this.name = name;
    }

}
