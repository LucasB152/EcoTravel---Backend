package be.ecotravel.back.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Entity
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Setter
    private String text;

    @Setter
    @Enumerated(EnumType.ORDINAL)
    private RequestStatusEnum status;

    @OneToOne(optional = false)
    private User user;

    protected Request() {}

    public Request(String text, RequestStatusEnum status, User user) {
        this.text = text;
        this.status = status;
        this.user = user;
    }

    @PrePersist
    public void prePersist() {
        this.id = UUID.randomUUID();
    }

}
