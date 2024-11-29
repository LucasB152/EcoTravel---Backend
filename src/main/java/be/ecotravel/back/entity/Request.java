package be.ecotravel.back.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Getter
@Entity
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(optional = false)
    private User user;

    protected Request() {}

    public Request(User user) {
        this.user = user;
    }

}
