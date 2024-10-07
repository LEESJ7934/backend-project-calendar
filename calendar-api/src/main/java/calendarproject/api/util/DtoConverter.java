package calendarproject.api.util;



import calendarproject.api.dto.EventDto;
import calendarproject.api.dto.NotificationDto;
import calendarproject.api.dto.ScheduleDto;
import calendarproject.api.dto.TaskDto;
import calendarproject.core.domain.entity.Schedule;
import calendarproject.core.exception.CalendarException;
import calendarproject.core.exception.ErrorCode;

public abstract class DtoConverter {

    public static ScheduleDto toForListDto(Schedule schedule) {
        switch (schedule.getScheduleType()) {
            case EVENT:
                return EventDto.builder()
                        .scheduleId(schedule.getId())
                        .startAt(schedule.getStartAt())
                        .endAt(schedule.getEndAt())
                        .title(schedule.getTitle())
                        .description(schedule.getDescription())
                        .writerId(schedule.getWriter().getId())
                        .build();
            case TASK:
                return TaskDto.builder()
                        .scheduleId(schedule.getId())
                        .taskAt(schedule.getStartAt())
                        .title(schedule.getTitle())
                        .description(schedule.getDescription())
                        .writerId(schedule.getWriter().getId())
                        .build();
            case NOTIFICATION:
                return NotificationDto.builder()
                        .scheduleId(schedule.getId())
                        .notifyAt(schedule.getStartAt())
                        .title(schedule.getTitle())
                        .writerId(schedule.getWriter().getId())
                        .build();
            default:
                throw new CalendarException(ErrorCode.BAD_REQUEST);
        }
    }
}
