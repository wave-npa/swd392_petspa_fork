package org.petspa.petcaresystem.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Staff")
public class Staff {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String staff_id;

    @OneToOne(mappedBy = "user_id")
    @JsonIgnore
    private AuthenUser user_id;
}
