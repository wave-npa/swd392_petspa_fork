package org.petspa.petcaresystem.medicine.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.pet.model.entity.MedicalRecord;

import java.io.Serializable;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Medicine")
public class Medicine implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "medicine_id", nullable = false)
    private String medicine_id;

    @Column(name = "medicine_name", nullable = false)
    private String medicine_name;

    @Column(name = "price", nullable = false)
    private float price;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @ManyToMany(mappedBy = "petMedicine")
    private Collection<MedicalRecord> medicalRecord;

}