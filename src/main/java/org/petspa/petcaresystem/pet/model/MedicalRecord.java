package org.petspa.petcaresystem.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "MedicalRecord")
public class  MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String record_id;

    @OneToOne(mappedBy = "pet_id")
    private AuthenUser pet_id;
}
