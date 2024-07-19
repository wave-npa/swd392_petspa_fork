package org.petspa.petcaresystem.schedule.repository;

import org.springframework.stereotype.Repository;

import org.petspa.petcaresystem.schedule.model.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    public Schedule findByScheduleId(Long scheduleId);
}
