package org.petspa.petcaresystem.schedule.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.petspa.petcaresystem.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ScheduleDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "detail_id")
    private Long detailId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Status status;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Schedule schedule;

}
