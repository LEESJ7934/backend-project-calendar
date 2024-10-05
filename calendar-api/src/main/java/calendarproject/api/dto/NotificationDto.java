package calendarproject.api.dto;

import calendarproject.core.domain.entity.ScheduleType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationDto implements ScheduleDto {
    private final Long scheduleId;
    private final Long writerId;
    private final String title;
    private final LocalDateTime notifyAt;

    @Override
    public ScheduleType getScheduleType() {
        return ScheduleType.NOTIFICATION;
    }
}
