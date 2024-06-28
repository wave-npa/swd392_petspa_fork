package org.petspa.petcaresystem.order.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.petspa.petcaresystem.appointment.model.Appointment;
import org.petspa.petcaresystem.authenuser.model.AuthenUser;

import java.io.Serializable;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "UserOrder")
public class UserOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userOrder_id")
    private Long userOrderId;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "order_date", nullable = false)
    private Date userOrderDate;

    @OneToOne(mappedBy = "userOrder")
    private Appointment appointment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private AuthenUser customer;
}
