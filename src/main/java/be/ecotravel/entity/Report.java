package be.ecotravel.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String text;

    private boolean edited;

    private LocalDateTime date;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private Destination destination;

    protected Report() {}

    public Report(UUID id, String text, User user, Destination destination) {
        this.id = id;
        this.text = text;
        this.user = user;
        this.destination = destination;
    }

    //region Persistence Function
    @PrePersist
    public void prePersist() {
        edited = false;
        date = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        edited = true;
    }
    //endregion

    //region Get Functions
    public UUID getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public boolean isEdited() {
        return edited;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public User getUser() {
        return user;
    }

    public Destination getDestination() {
        return destination;
    }
    //endregion

    //region Set Functions
    public void setText(String text) {
        this.text = text;
    }

    public void setEdited(boolean value) {
        this.edited = value;
    }
    //endregion

}
