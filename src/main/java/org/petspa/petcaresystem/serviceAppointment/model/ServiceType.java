package org.petspa.petcaresystem.serviceAppointment.model;

import java.util.Collection;

import org.petspa.petcaresystem.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ServiceType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "serviceType_id")
    private Long serviceTypeId;

    private String typeName;

    private Status status;

    @ManyToMany(mappedBy = "typeOfService")
    private Collection<Services> services;

}
