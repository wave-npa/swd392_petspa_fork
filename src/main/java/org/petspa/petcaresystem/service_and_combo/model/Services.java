package org.petspa.petcaresystem.service_and_combo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.petspa.petcaresystem.enums.Status;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "Services")
public class Services implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String serviceId;

    @Column(name = "serviceName")
    private String serviceName;

    @Column(name = "description")
    private String description;

    @Column(name = "serviceType")
    private int serviceType;

    @Column(name = "price")
    private float price;

    @Column(name = "discountPercent")
    private int discountPercent;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

//    @ManyToMany
    @JoinTable(
            name = "Services_Combo",
            joinColumns = @JoinColumn(name = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "Combo_id")
    )
    @JsonIgnore
    private Set<Combo> combos = new HashSet<>();
}
