package org.petspa.petcaresystem.appointment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.petspa.petcaresystem.doctor.model.Doctor;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.pet.model.entity.Pet;
import org.petspa.petcaresystem.service_and_combo.model.Services;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Appointment")
public class Appointment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private Long appointmentId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctorId;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet petId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "create_date", nullable = false)
    private Date createDate;

    @Column(name = "appointment_type", nullable = false)
    private String appointmentType;

    @Column(name = "appointment_time", nullable = false)
    private LocalDateTime appointmentTime;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "service_id", nullable = false)
    private Services serviceId;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "combo_id", nullable = false)
    private Services comboId;
}
