package org.petspa.petcaresystem.serviceAppointment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.petspa.petcaresystem.appointment.model.payload.Appointment;
import org.petspa.petcaresystem.enums.Status;

import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Services")
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "service_id")
    private Long serviceId;

    @Column(name = "service_name", nullable = false)
    private String serviceName;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToMany
    @JoinTable(
        name = "type_service", 
        joinColumns = @JoinColumn(name = "service_id"), 
        inverseJoinColumns = @JoinColumn(name = "serviceType_id"))
    private Collection<ServiceType> typeOfService;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "discount_percent", nullable = true)
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
