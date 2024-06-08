package org.petspa.petcaresystem.order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.petspa.petcaresystem.appointment.model.Appointment;
import org.petspa.petcaresystem.customer.model.Customer;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "order_date", nullable = false)
    private Date orderDate;

//    @OneToOne
//    @JsonIgnore
    @Column(name = "appointment_id", nullable = false)
    private Appointment appointmentId;

//    @ManyToOne
//    @JsonIgnore
    @Column(name = "customer_id", nullable = false)
    private Customer customerId;
}
