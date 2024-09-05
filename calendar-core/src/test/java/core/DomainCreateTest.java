package core;

import core.domain.entity.Schedule;
import core.domain.entity.ScheduleType;
import core.domain.entity.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;

//도메인 테스트
public class DomainCreateTest {

    @Test
    void eventCreate() {
        final User me = new User("name", "email","pw",LocalDate.now());
        final Schedule taskSchedule = Schedule.task("할일","빨래하기", LocalDateTime.now(),me);
        assertEquals(taskSchedule.getScheduleType(), ScheduleType.TASK);
        assertEquals(taskSchedule.toTask().getTitle(), "할일");
    }

}
