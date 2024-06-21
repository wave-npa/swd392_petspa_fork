package org.petspa.petcaresystem.service_and_combo.model;

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
public class Combo implements Serializable {

    @Id
    @Column(name = "comboId")
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

    // @OneToMany(mappedBy = "combo", cascade = CascadeType.ALL)
    // @PrimaryKeyJoinColumn
    // @EqualsAndHashCode.Exclude
    // private Collection<Appointment> appointment;
}
