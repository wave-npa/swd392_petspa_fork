package org.petspa.petcaresystem.pet.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.petspa.petcaresystem.authenuser.model.entity.AuthenUser;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MedicalRecord")
public class  MedicalRecord implements Serializable {

    @Id
    private String record_id;

//    @OneToOne
    private AuthenUser pet_id;
}
