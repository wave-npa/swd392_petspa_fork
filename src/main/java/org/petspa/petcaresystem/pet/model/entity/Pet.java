package org.petspa.petcaresystem.pet.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.petspa.petcaresystem.customer.model.Customer;
import org.petspa.petcaresystem.enums.Gender;
import org.petspa.petcaresystem.enums.Species;
import org.petspa.petcaresystem.enums.Status;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "Pet")
public class Pet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String pet_id;

    @Column(name = "full_name")
    private String pet_name;

    @Column(name = "age")
    private int age;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "species")
    private Species species;

    @Column(name = "type_of_species")
    private String type_of_species;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

//    @ManyToOne
    private Customer customer_id;


}
