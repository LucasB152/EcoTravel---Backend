package be.ecotravel.ecotravelback.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String text;

    private boolean isEdited;

    private LocalDateTime date;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private Destination destination;

    protected Report() {}

    public Report(UUID id, String text, boolean isEdited, LocalDateTime date, User user, Destination destination) {
        this.id = id;
        this.text = text;
        this.isEdited = isEdited;
        this.date = date;
        this.user = user;
        this.destination = destination;
    }

    //region Get Functions
    public UUID getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public boolean isEdited() {
        return isEdited;
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

    public void setEdited(boolean isEdited) {
        this.isEdited = isEdited;
    }
    //endregion

}
