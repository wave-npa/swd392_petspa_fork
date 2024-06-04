package org.petspa.petcaresystem.appointment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.petspa.petcaresystem.doctor.model.Doctor;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.pet.model.entity.Pet;
import org.petspa.petcaresystem.service_and_combo.model.Services;

<<<<<<< HEAD

import java.time.LocalDateTime;
import java.util.Date;
=======
import java.util.HashSet;
import java.util.Set;
>>>>>>> 9d2f3f6e7400c6e1b7ced54009e8623c2153260e

@Entity
@Getter
@Setter
@Table(name = "Appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String appointmentId;

    @ManyToOne
    private Doctor doctorId;

    @OneToOne(mappedBy = "petId")
    private Pet petId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

<<<<<<< HEAD
    @Column(name = "create_date")
    private Date create_date;

    @Column(name = "appointment_type")
    private String appointment_type;

    @Column(name = "appointment_time")
    private LocalDateTime appointment_time;

    @ManyToOne
    @JsonIgnore
    private Services service_id;

    @ManyToOne
    @JsonIgnore
    private Services Combo_id;
=======
    @Column(name = "createdDate")
    private int createdDate;

    @Column(name = "appointmentTime")
    private int appointmentTime;

    @OneToOne(mappedBy = "serviceId")
    private Services service;

//     @ManyToMany
//     @JoinTable(
//             name = "Appointment_Combo",
//             joinColumns = @JoinColumn(name = "appointment_id"),
//             inverseJoinColumns = @JoinColumn(name = "Combo_id")
//     )
//     private Set<Combo> combos = new HashSet<>();
>>>>>>> 9d2f3f6e7400c6e1b7ced54009e8623c2153260e
}
