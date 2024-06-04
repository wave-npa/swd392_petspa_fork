package org.petspa.petcaresystem.pet.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.petspa.petcaresystem.authenuser.model.entity.AuthenUser;

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
