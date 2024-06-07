package org.petspa.petcaresystem.doctor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.petspa.petcaresystem.authenuser.model.entity.AuthenUser;
import org.petspa.petcaresystem.department.model.Department;

import java.io.Serializable;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Doctor")
public class Doctor implements Serializable {

    @Id
    private String doctor_id;

//    @OneToOne
    @JsonIgnore
    private AuthenUser user_id;

//    @ManyToOne
    private Department department_id;
}
