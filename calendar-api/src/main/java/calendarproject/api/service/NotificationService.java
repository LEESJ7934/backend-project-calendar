package calendarproject.api.service;

import calendarproject.api.dto.AuthUser;
import calendarproject.api.dto.NotificationCreateReq;
import calendarproject.core.domain.entity.Schedule;
import calendarproject.core.domain.entity.User;
import calendarproject.core.domain.entity.repository.ScheduleRepository;
import calendarproject.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final ScheduleRepository scheduleRepository;
    private final UserService userService;

    @Transactional
    public void create(NotificationCreateReq req, AuthUser authUser) {
        final User writer = userService.findByUserId(authUser.getId());
        req.getRepeatTimes()
                .forEach(notifyAt ->
                        scheduleRepository.save(Schedule.notification(req.getTitle(), notifyAt, writer)));
    }
}
