package org.petspa.petcaresystem.schedule.repository;

import org.petspa.petcaresystem.schedule.model.entity.Schedule;
import org.petspa.petcaresystem.schedule.model.entity.ScheduleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleDetailRepository extends JpaRepository<ScheduleDetail, Long> {
    public List<ScheduleDetail> findBySchedule(Schedule schedule);
    public ScheduleDetail findByDetailId(Long scheduleDetail);
}
