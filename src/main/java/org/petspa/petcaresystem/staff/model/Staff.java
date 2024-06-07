package org.petspa.petcaresystem.staff.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.petspa.petcaresystem.authenuser.model.entity.AuthenUser;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "Staff")
public class Staff implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String staff_id;

//    @OneToOne
    @JsonIgnore
    private AuthenUser user_id;
}
