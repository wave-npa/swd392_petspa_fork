package org.petspa.petcaresystem.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.petspa.petcaresystem.enums.Gender;
import org.petspa.petcaresystem.enums.Status;

@Entity
@Getter
@Setter
@Table(name = "AuthenUser")
public class AuthenUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String user_id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "full_name")
    private String full_name;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @OneToOne(mappedBy = "role_id")
    @JsonIgnore
    private Role role_id;


}
