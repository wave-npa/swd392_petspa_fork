package org.petspa.petcaresystem.serviceAppointment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.petspa.petcaresystem.appointment.model.Appointment;
import org.petspa.petcaresystem.enums.Status;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Combo")
public class Combo {

    @Id
    @Column(name = "combo_id")
    private String comboId;

    @Column(name = "comboName")
    private String comboName;

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
    @JoinTable(
        name = "service_combo", 
        joinColumns = @JoinColumn(name = "combo_id"), 
        inverseJoinColumns = @JoinColumn(name = "service_id"))
    private Collection<Services> services;

}
