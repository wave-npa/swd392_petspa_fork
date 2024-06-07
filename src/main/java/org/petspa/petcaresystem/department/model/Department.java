package org.petspa.petcaresystem.department.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Department")
public class Department implements Serializable {

    @Id
    private String department_id;

    @Column(name = "department_name")
    private String department_name;

    @Column(name = "address")
    private String address;
}
