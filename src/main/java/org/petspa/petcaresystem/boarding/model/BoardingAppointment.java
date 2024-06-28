package org.petspa.petcaresystem.boarding.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

import org.petspa.petcaresystem.appointment.model.Appointment;
import org.petspa.petcaresystem.boarding_detail.model.BoardingDetail;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.shelter.model.Shelter;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
    @Column(name = "boardingId")
    private Long boardingId;

    private LocalDateTime boardingTime;

    private Status status;

    @OneToOne(mappedBy = "boardingAppointment")
    private Appointment appointment;

    @OneToMany(mappedBy = "boardingAppointment", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Collection<BoardingDetail> boardingDetail;

    @ManyToOne
    @JoinColumn(name = "shelter_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Shelter shelter;

}
