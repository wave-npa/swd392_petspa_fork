package org.petspa.petcaresystem.service_and_combo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.petspa.petcaresystem.appointment.model.Appointment;
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
    private String ComboId;

    @Column(name = "comboName")
    private String ComboName;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private float price;

    @Column(name = "discountPercent")
    private int discountPercent;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @ManyToMany
    private Set<Services> services = new HashSet<>();

}
