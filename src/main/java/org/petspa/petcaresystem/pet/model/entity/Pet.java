package org.petspa.petcaresystem.pet.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.petspa.petcaresystem.customer.model.Customer;
import org.petspa.petcaresystem.enums.Gender;
import org.petspa.petcaresystem.enums.Species;
import org.petspa.petcaresystem.enums.Status;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Pet")
public class Pet implements Serializable {

    @Id
    @Column(name = "pet_id")
    private String petId;

    @Column(name = "pet_name", nullable = false)
    private String petName;

    @Column(name = "age", nullable = false)
    private int age;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "species", nullable = false)
    private Species species;

    @Column(name = "type_of_species", nullable = false)
    private String typeOfSpecies;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customerId;


}
