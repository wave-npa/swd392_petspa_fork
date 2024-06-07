package org.petspa.petcaresystem.pet.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.petspa.petcaresystem.authenuser.model.entity.AuthenUser;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "MedicalRecord")
public class  MedicalRecord implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String record_id;

//    @OneToOne
    private AuthenUser pet_id;
}
