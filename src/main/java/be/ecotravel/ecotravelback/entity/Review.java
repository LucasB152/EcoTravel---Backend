package be.ecotravel.ecotravelback.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private int score;

    private String comment;

    private LocalDateTime createdAt;

    private boolean isEdited;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private Destination destination;

    protected Review() {}

    public Review(int score, String comment, LocalDateTime createdAt, boolean isEdited, User user, Destination destination) {
        this.score = score;
        this.comment = comment;
        this.createdAt = createdAt;
        this.isEdited = isEdited;
        this.user = user;
        this.destination = destination;
    }

    //region Get Functions
    public UUID getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public String getComment() {
        return comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
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

    //region Set functions
    public void setId(UUID id) {
        this.id = id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setEdited(boolean isEdited) {
        this.isEdited = isEdited;
    }
    //endregion
}
