package org.petspa.petcaresystem.boarding_detail.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;

import jakarta.persistence.*;
import org.petspa.petcaresystem.boarding.model.entity.BoardingAppointment;
import org.petspa.petcaresystem.enums.Status;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.petspa.petcaresystem.medicine.model.entity.Medicine;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BoardingDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "detail_id")
    private Long detailId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Date date;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "boardingAppointment")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private BoardingAppointment boardingAppointment;

    @ManyToMany
    @JoinTable(
        name = "medcine_used", 
        joinColumns = @JoinColumn(name = "medicine_id"), 
        inverseJoinColumns = @JoinColumn(name = "detail_id"))
    private Collection<Medicine> usedMedicine;

}
