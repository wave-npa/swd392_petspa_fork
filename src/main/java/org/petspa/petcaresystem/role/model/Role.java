package org.petspa.petcaresystem.role.model;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.petspa.petcaresystem.enums.Status;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "Role")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String role_id;

    @Column(name = "role_name")
    private String role_name;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;


}
