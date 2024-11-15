package be.ecotravel.ecotravelback.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String text;

    private boolean isEdited;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private Destination destination;

    protected Report() {}

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

    public User getUser() {
        return user;
    }

    public Destination getDestination() {
        return destination;
    }
    //endregion

    public void setText(String text) {
        this.text = text;
    }

    public void setEdited(boolean isEdited) {
        this.isEdited = isEdited;
    }

}
