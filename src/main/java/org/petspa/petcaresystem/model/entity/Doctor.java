package org.petspa.petcaresystem.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Doctor")
public class Doctor {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String doctor_id;

    @OneToOne(mappedBy = "user_id")
    @JsonIgnore
    private AuthenUser user_id;

    @ManyToOne
    private Department department_id;
}
