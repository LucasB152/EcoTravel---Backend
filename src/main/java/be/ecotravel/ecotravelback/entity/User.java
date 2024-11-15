package be.ecotravel.ecotravelback.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String lastName;

    private String email;

    private String profilePicturePath;

    @ManyToOne(optional = false)
    private UserRole userRole;

    protected User() {}

    //region Get Functions
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getProfilePicturePath() {
        return profilePicturePath;
    }

    public UserRole getRole() {
        return userRole;
    }
    //endregion

    //region Set Functions
    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProfilePicturePath(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
    }

    public void setRole(UserRole userRole) {
        this.userRole = userRole;
    }
    //endregion

}
