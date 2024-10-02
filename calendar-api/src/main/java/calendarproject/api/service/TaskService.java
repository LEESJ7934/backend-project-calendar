package calendarproject.api.service;

import calendarproject.api.dto.AuthUser;
import calendarproject.api.dto.TaskCreateReq;
import calendarproject.core.domain.entity.Schedule;
import calendarproject.core.domain.entity.repository.ScheduleRepository;
import calendarproject.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final UserService userService;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public void create(TaskCreateReq req, AuthUser authUser) {
        final Schedule taskSchedule = Schedule.task(req.getTitle(), req.getDescription(), req.getTaskAt(), userService.getOrThrowById(authUser.getId()));
        scheduleRepository.save(taskSchedule);
    }

}
