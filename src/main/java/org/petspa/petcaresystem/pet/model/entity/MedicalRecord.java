package org.petspa.petcaresystem.pet.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.petspa.petcaresystem.medicine.model.Medicine;

import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MedicalRecord")
public class  MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "record_id")
    private Long recordId;

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
