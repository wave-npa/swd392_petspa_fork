package org.petspa.petcaresystem.staff.model;

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
@Table(name = "Staff")
public class Staff implements Serializable {

    @Id
    @Column(name = "staff_id")
    private String staffId;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", nullable = false)
    private AuthenUser user_id;
}
