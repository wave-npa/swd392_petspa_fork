package org.petspa.petcaresystem.department.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "Department")
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String department_id;

    @Column(name = "department_name")
    private String department_name;

    @Column(name = "address")
    private String address;
}
