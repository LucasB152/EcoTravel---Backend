package be.ecotravel.back.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String price;

    private String capacity;

    private boolean isVisible = true;

    private String contactPhone;

    private String contactEmail;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private DestinationType destinationType;

    @ManyToOne(optional = false)
    private Address address;

    @ManyToMany
    private Set<Tag> tag;

}
