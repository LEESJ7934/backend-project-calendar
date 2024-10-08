package calendarproject.core.domain;


import calendarproject.core.domain.entity.Schedule;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Task {


    private Schedule schedule;

    public Task(Schedule schedule) {
        this.schedule = schedule;
    }

    public String getTitle(){
        return schedule.getTitle();
    }
}
