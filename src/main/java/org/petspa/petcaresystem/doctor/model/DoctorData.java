package org.petspa.petcaresystem.doctor.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DoctorData {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "doctor_id")
        private Long doctorId;

        private Long userId;

        private Long departmentId;
        
        private Long scheduleId;
}
