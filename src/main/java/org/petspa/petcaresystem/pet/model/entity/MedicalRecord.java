package org.petspa.petcaresystem.pet.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.petspa.petcaresystem.authenuser.model.AuthenUser;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.medicine.model.entity.Medicine;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.petspa.petcaresystem.medicine.model.entity.Medicine;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MedicalRecord")
public class  MedicalRecord implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medicalrecord_id", nullable = false)
    private Long medicalrecord_id;

    @Column(name = "medical_description")
    private String medical_description;

    @Column(name = "last_update", nullable = false)
    private LocalDateTime last_update;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
//    co the co them
    @ManyToOne
    @JoinColumn(name = "pet")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Pet pet;

    @ManyToMany
    @JoinTable(
            name = "pet_medicine",
            joinColumns = @JoinColumn(name = "medicine_id"),
            inverseJoinColumns = @JoinColumn(name = "record_id"))
    private Collection<Medicine> petMedicine;

}
