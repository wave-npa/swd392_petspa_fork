package org.petspa.petcaresystem.admin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.petspa.petcaresystem.authenuser.model.entity.AuthenUser;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Admin")
public class Admin implements Serializable {

    @Id
    @Column(name = "admin_id")
    private String adminId;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", nullable = false)
    private AuthenUser userId;

}
