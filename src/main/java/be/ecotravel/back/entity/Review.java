package be.ecotravel.back.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private int score;

    private String title;

    private String comment;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private boolean edited;
    
    private LocalDateTime editedAt;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private Destination destination;

    public Review() {

    }

    @PrePersist
    public void prePersist() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }

    public Review(int score, String comment, User user, Destination destination) {
        this.score = score;
        this.comment = comment;
        this.user = user;
        this.destination = destination;
    }
}
