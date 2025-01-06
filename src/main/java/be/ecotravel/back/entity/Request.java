package be.ecotravel.back.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    String contactPhone;

    @Column(nullable = false)
    String motivation;

    String company;

    String identifier;

    String websiteUrl;

    String description;

    @Column(nullable = false)
    int services;

    @Enumerated(EnumType.STRING)
    private HostStatusEnum hostStatus;

    @Enumerated(EnumType.ORDINAL)
    private RequestStatusEnum requestStatus = RequestStatusEnum.WAITING;

    @OneToOne(optional = false)
    private User user;

    public Request() {

    }

}
