package org.petspa.petcaresystem.authenuser.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.petspa.petcaresystem.appointment.model.Appointment;
import org.petspa.petcaresystem.enums.Gender;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.order.model.UserOrder;
import org.petspa.petcaresystem.pet.model.entity.Pet;
import org.petspa.petcaresystem.review.model.Review;
import org.petspa.petcaresystem.role.model.Role;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "AuthenUser")
public class AuthenUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId")
    private Long userId;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "full_name", nullable = false)
    private String full_name;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone", nullable = false)
    private Long phone;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime create_date;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "role_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Role role;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Collection<UserOrder> order;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Collection<Pet> ownedPet;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Collection<Review> writtenReview;
}
