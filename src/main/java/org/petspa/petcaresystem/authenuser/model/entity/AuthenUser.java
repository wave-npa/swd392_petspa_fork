package org.petspa.petcaresystem.authenuser.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.petspa.petcaresystem.enums.Gender;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.role.model.Role;

@Entity
@Getter
@Setter
@Table(name = "AuthenUser")
public class AuthenUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OneToOne
    @Column(name = "user_id")
    private String user_id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "full_name")
    private String full_name;

    @Enumerated(EnumType.STRING)
    @ManyToOne
    @JoinColumn(name = "gender", nullable = false)
    @JoinColumn(name = "gender")
    private Gender gender;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Enumerated(EnumType.STRING)
//    @ManyToOne
    @Column(name = "Status", nullable = false)
//    @JoinColumn(name = "")
    private Status status;

    @OneToOne
    @JsonIgnore
    private Role role_id;

}
