package org.petspa.petcaresystem.schedule.repository;

import org.petspa.petcaresystem.schedule.model.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}
