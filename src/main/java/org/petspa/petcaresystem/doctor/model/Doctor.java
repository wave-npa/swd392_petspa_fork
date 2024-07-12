package org.petspa.petcaresystem.doctor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.petspa.petcaresystem.appointment.model.payload.Appointment;
import org.petspa.petcaresystem.authenuser.model.payload.AuthenUser;
import org.petspa.petcaresystem.department.model.entity.Departments;
import org.petspa.petcaresystem.schedule.model.Schedule;

import java.io.Serializable;
import java.util.Collection;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Doctor")
public class Doctor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "doctor_id")
    private Long doctorId;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", nullable = false)
    private AuthenUser user;

    @ManyToOne
    @JoinColumn(name = "department_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Departments department;

    @ManyToMany(mappedBy = "bookedDoctor")
    @JsonIgnore
    private Collection<Appointment> appointment;


//    @MapsId
    @JoinColumn(name = "schedule_id", nullable = true)
    @OneToOne
    @JsonIgnore
    private Schedule schedule;
}
