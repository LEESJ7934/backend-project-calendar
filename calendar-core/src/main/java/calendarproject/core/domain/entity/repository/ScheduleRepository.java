package calendarproject.core.domain.entity.repository;

import calendarproject.core.domain.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByWriter_Id(Long id);
}
