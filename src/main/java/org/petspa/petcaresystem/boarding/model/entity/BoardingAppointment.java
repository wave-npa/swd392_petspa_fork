package org.petspa.petcaresystem.boarding.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

import jakarta.persistence.*;
import org.petspa.petcaresystem.appointment.model.payload.Appointment;
import org.petspa.petcaresystem.boarding_detail.model.BoardingDetail;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.shelter.model.entity.Shelter;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BoardingAppointment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "boarding_id")
    private Long boardingId;

    @Column(name = "boarding_time")
    private LocalDateTime boardingTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @OneToOne(mappedBy = "boardingAppointment")
    @JoinColumn(name = "appointment")
    private Appointment appointment;

    @OneToMany(mappedBy = "boardingAppointment", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Collection<BoardingDetail> boardingDetail;

    @ManyToOne
    @JoinColumn(name = "shelterId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Shelter shelter;

//    @OneToMany(mappedBy = "shelter", cascade = CascadeType.ALL)
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    @JsonIgnore
//    private Collection<Shelter> shelter;
}
