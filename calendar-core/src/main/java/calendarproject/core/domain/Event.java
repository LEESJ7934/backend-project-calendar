package calendarproject.core.domain;


import calendarproject.core.domain.entity.Schedule;
import calendarproject.core.domain.entity.User;
import calendarproject.core.util.Period;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


public class Event {
    private final Schedule schedule;

    public Event(Schedule schedule) {
        this.schedule = schedule;
    }

    public Long getId() {
        return this.schedule.getId();
    }
    public LocalDateTime getStartAt() {
        return schedule.getStartAt();
    }

    public LocalDateTime getEndAt() {
        return schedule.getEndAt();
    }

    public User getWriter() {
        return this.schedule.getWriter();
    }

    public boolean isOverlapped(LocalDateTime startAt, LocalDateTime endAt) {
        return this.getStartAt().isBefore(endAt) && startAt.isBefore(this.getEndAt());
    }

    public String getTitle() {
        return schedule.getTitle();
    }

    public Period getPeriod() {
        return Period.of(schedule.getStartAt(), schedule.getEndAt());
    }
}
