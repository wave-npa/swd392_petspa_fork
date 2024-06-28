package org.petspa.petcaresystem.shelter.model;

import java.io.Serializable;
import java.util.Collection;

import org.petspa.petcaresystem.boarding.model.BoardingAppointment;
import org.petspa.petcaresystem.enums.Status;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    private String shelterId;

    private String shelterName;

    private Status status;

    @OneToMany(mappedBy = "shelter", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Collection<BoardingAppointment> boarding;

}
