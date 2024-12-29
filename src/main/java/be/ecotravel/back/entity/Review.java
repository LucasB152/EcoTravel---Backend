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
}
