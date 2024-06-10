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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private String doctorId;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", nullable = false)
    private AuthenUser userId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "department_id", nullable = false)
    private Department departmentId;
}
