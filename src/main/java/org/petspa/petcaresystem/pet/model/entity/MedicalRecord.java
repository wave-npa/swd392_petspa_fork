package org.petspa.petcaresystem.pet.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.petspa.petcaresystem.authenuser.model.entity.AuthenUser;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.medicine.model.entity.Medicine;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MedicalRecord")
public class  MedicalRecord implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medicalrecord_id", nullable = false)
    private Long medicalrecord_id;

    @OneToOne
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet petId;

    @Column(name = "medical_description")
    private String medical_description;

    @Column(name = "last_update", nullable = false)
    private LocalDateTime last_update;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
//    co the co them
    @ManyToMany
    @JoinTable(
            name = "medicine",
            joinColumns = { @JoinColumn(name = "record_id") },
            inverseJoinColumns = { @JoinColumn(name = "medicine_id") }
    )
    private List<Medicine> medicines = new ArrayList<>();
}
