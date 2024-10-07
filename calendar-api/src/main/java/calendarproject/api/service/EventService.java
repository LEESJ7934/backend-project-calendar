package calendarproject.api.service;

import calendarproject.api.dto.AuthUser;
import calendarproject.api.dto.EventCreateReq;
import calendarproject.core.domain.RequestStatus;
import calendarproject.core.domain.entity.Engagement;
import calendarproject.core.domain.entity.Schedule;
import calendarproject.core.domain.entity.repository.EngagementRepository;
import calendarproject.core.domain.entity.repository.ScheduleRepository;
import calendarproject.core.exception.CalendarException;
import calendarproject.core.exception.ErrorCode;
import calendarproject.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class EventService {
    private final UserService userService;
    private final ScheduleRepository scheduleRepository;
    private final EngagementRepository engagementRepository;
    private final EmailService emailService;

    @Transactional
    public void create(EventCreateReq req, AuthUser authUser) {
        // attendees 의 스케쥴 시간과 겹치지 않는지?
        final List<Engagement> engagementList =
                engagementRepository.findAllByAttendeeIdInAndSchedule_EndAtAfter(req.getAttendeeIds(),
                        req.getStartAt());
        if (engagementList
                .stream()
                .anyMatch(e -> e.getEvent().isOverlapped(req.getStartAt(), req.getEndAt())
                        && e.getStatus() == RequestStatus.ACCEPTED)) {
            throw new CalendarException(ErrorCode.EVENT_CREATE_OVERLAPPED_PERIOD);
        }
        final Schedule eventSchedule = Schedule.event(req.getTitle(), req.getDescription(), req.getStartAt(), req.getEndAt(), userService.findByUserId(authUser.getId()));
        scheduleRepository.save(eventSchedule);
        req.getAttendeeIds().stream()
                .map(userService::findByUserId)
                .forEach(user -> {
                    final Engagement e = engagementRepository.save(new Engagement(eventSchedule, user));
                    emailService.sendEngagement(e);
                });
    }

}
