package org.petspa.petcaresystem.customer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.petspa.petcaresystem.authenuser.model.entity.AuthenUser;
import org.petspa.petcaresystem.pet.model.entity.Pet;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "Customer")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String customer_id;

//    @OneToOne
    @JsonIgnore
    private AuthenUser user_id;
}
