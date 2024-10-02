package calendarproject.core.domain;

import calendarproject.core.domain.entity.Schedule;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Notification {

    private Schedule schedule;

    public Notification(Schedule schedule) {
        this.schedule = schedule;
    }

}
