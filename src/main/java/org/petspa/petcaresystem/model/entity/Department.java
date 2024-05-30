package org.petspa.petcaresystem.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String department_id;

    @Column(name = "department_name")
    private String department_name;

    @Column(name = "address")
    private String address;
}
