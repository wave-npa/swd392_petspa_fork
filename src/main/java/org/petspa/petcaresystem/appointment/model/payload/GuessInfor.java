package org.petspa.petcaresystem.appointment.model.payload;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Guess_information")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GuessInfor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long guessId;
    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "appointment")
    private Long appointmentId;
}
