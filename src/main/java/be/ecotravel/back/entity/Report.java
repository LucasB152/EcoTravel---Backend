package be.ecotravel.back.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Setter
    private String text;

    @Setter
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

}
