package org.petspa.petcaresystem.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.petspa.petcaresystem.enums.Status;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "Combo")
public class Combo {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String Combo_id;

    @Column(name = "service_name")
    private String Combo_name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private float price;

    @Column(name = "discount_percent")
    private int discount_percent;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @ManyToMany
    private Set<Services> services = new HashSet<>();

    @ManyToMany
    private Set<Appointment> appointments = new HashSet<>();
}
