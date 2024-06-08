package org.petspa.petcaresystem.service_and_combo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.petspa.petcaresystem.enums.Status;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Combo")
public class Combo implements Serializable {

    @Id
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
}
