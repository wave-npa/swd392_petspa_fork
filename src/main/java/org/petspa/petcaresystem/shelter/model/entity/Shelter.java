package org.petspa.petcaresystem.shelter.model.entity;

import java.io.Serializable;
import java.util.Collection;

import jakarta.persistence.*;
import org.petspa.petcaresystem.boarding.model.entity.BoardingAppointment;
import org.petspa.petcaresystem.enums.ShelterStatus;
import org.petspa.petcaresystem.enums.Status;

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
public class Shelter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "shelter_id")
    private Long shelterId;

    @Column(name = "shelter_name")
    private String shelterName;

    @Enumerated(EnumType.STRING)
    @Column(name = "shelter_status")
    private ShelterStatus shelterStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

//    @ManyToOne
//    @JoinColumn(name = "shelter")
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    @JsonIgnore
//    private BoardingAppointment boarding;

//    @OneToMany(mappedBy = "shelter", cascade = CascadeType.ALL)
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    @JsonIgnore
//    private Collection<BoardingAppointment> boarding;

}
