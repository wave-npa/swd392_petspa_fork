package org.petspa.petcaresystem.appointment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.core.util.Json;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.apache.commons.lang3.builder.HashCodeExclude;
import org.petspa.petcaresystem.authenuser.model.entity.AuthenUser;
import org.petspa.petcaresystem.doctor.model.Doctor;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.order.model.UserOrder;
import org.petspa.petcaresystem.pet.model.entity.Pet;
import org.petspa.petcaresystem.service_and_combo.model.Combo;
import org.petspa.petcaresystem.service_and_combo.model.Services;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Appointment")
public class Appointment implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "appointment_id")
    private Long appointmentId;

    @ManyToMany
    private Collection<AuthenUser> doctor;

    // @ManyToOne
    // @MapsId
    // @JoinColumn(name = "pet")
    // @EqualsAndHashCode.Exclude
    // @ToString.Exclude
    // private Pet pet;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "create_date")
    private Date create_date;

    @Column(name = "appointment_type")
    private String appointment_type;

    @Column(name = "appointment_time", nullable = false)
    private LocalDateTime appointmentTime;

    @OneToOne
    @MapsId
    @JoinColumn(name = "serviceId", nullable = true)
    @JsonIgnore
    private Services service;

    @OneToOne
    @MapsId
    @JoinColumn(name = "serviceId", nullable = true)
    @JsonIgnore
    private Combo combo;

    @OneToOne
    @MapsId
    @JsonIgnore
    @JoinColumn(name = "userOrderId", nullable = true)
    private UserOrder userOrder;
}
