package org.petspa.petcaresystem.order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.petspa.petcaresystem.appointment.model.Appointment;
import org.petspa.petcaresystem.authenuser.model.entity.AuthenUser;

import java.io.Serializable;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Order")
public class Orders implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "order_date", nullable = false)
    private Date orderDate;

    @OneToOne
    @Column(name = "appointment_id", nullable = false)
    private Appointment appointmentId;

    @ManyToOne
    @Column(name = "customer_id", nullable = false)
    private AuthenUser customerId;
}
