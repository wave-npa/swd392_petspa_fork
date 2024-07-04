package org.petspa.petcaresystem.serviceAppointment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.petspa.petcaresystem.appointment.model.Appointment;
import org.petspa.petcaresystem.enums.Status;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Services")
public class Services {

    @Id
    @Column(name = "service_id")
    private Long serviceId;

    @Column(name = "service_name", nullable = false)
    private String serviceName;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToMany
    @JoinTable(
        name = "type_service", 
        joinColumns = @JoinColumn(name = "serviceType_id"), 
        inverseJoinColumns = @JoinColumn(name = "service_id"))
    private Collection<ServiceType> typeOfService;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "discount_percent", nullable = false)
    private int discountPercent;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @ManyToMany(mappedBy = "bookedService")
    @JsonIgnore
    private Collection<Appointment> appointment;

    @ManyToMany(mappedBy = "services")
    @JsonIgnore
    private Collection<Combo> serviceCombo;
}
