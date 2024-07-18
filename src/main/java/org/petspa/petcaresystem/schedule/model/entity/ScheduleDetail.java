package org.petspa.petcaresystem.schedule.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import org.petspa.petcaresystem.enums.Status;

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

    @Column(name = "startTime")
    private LocalDateTime startTime;

    @Column(name = "endTime")
    private LocalDateTime endTime;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Schedule schedule;
}
