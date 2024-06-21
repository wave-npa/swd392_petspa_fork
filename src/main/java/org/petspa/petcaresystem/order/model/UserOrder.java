package org.petspa.petcaresystem.order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.petspa.petcaresystem.appointment.model.Appointment;
import org.petspa.petcaresystem.authenuser.model.entity.AuthenUser;

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
    @Column(name = "userOrderId")
    private Long userOrderId;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "order_date", nullable = false)
    private Date userOrderDate;

    @OneToOne(mappedBy = "userOrder")
    private Appointment appointment;

    @ManyToOne
    @JoinColumn(name = "customer")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private AuthenUser customer;
}
