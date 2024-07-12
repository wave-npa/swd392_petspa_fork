package org.petspa.petcaresystem.appointment.model.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.petspa.petcaresystem.boarding.model.entity.BoardingAppointment;
import org.petspa.petcaresystem.doctor.model.Doctor;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.order.model.UserOrder;
import org.petspa.petcaresystem.pet.model.entity.Pet;
import org.petspa.petcaresystem.review.model.Review;
import org.petspa.petcaresystem.serviceAppointment.model.Services;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Appointment")
public class Appointment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "appointment_id")
    private Long appointmentId;

    @ManyToMany
    @JoinTable(
            name = "doctor_booked",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id"))
    private Collection<Doctor> bookedDoctor;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Pet pet;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "create_date")
    private LocalDate create_date;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @Column(name = "startTime", nullable = false)
    private LocalDateTime startTime;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @Column(name = "endTime", nullable = true)
    private LocalDateTime endTime;

    @ManyToMany
    @JoinTable(
            name = "service_booked",
            joinColumns = @JoinColumn(name = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "appointment_id"))
    private Collection<Services> bookedService;

    @OneToOne
    // @MapsId
    @JoinColumn(name = "userOrder_id", nullable = true)
    @JsonIgnore
//    @OneToOne(mappedBy = "appointment")
    private UserOrder userOrder;


    // @MapsId
    @OneToOne
    @JoinColumn(name = "boardingAppointment_id", nullable = true)
    @JsonIgnore
//    @OneToOne(mappedBy = "appointment")
    private BoardingAppointment boardingAppointment;

    // @MapsId
    @OneToOne
    @JoinColumn(name = "review_id", nullable = true)
//    @OneToOne(mappedBy = "appointment")
    @JsonIgnore
    private Review review;
}
