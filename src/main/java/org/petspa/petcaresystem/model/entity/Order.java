package org.petspa.petcaresystem.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String order_id;

    @Column(name = "price")
    private float price;

    @Column(name = "order_date")
    private int order_date;

    @OneToOne(mappedBy = "appointment_id")
    private AuthenUser appointment_id;

    @ManyToOne
    private Customer customer_id;
}
