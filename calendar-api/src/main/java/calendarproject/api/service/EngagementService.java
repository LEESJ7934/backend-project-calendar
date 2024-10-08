package calendarproject.api.service;


import calendarproject.api.dto.AuthUser;
import calendarproject.core.domain.RequestReplyType;
import calendarproject.core.domain.RequestStatus;
import calendarproject.core.domain.entity.repository.EngagementRepository;
import calendarproject.core.exception.CalendarException;
import calendarproject.core.domain.entity.Engagement;
import calendarproject.core.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EngagementService {

    private final EngagementRepository engagementRepository;

    @Transactional
    public RequestStatus update(AuthUser authUser, Long engagementId, RequestReplyType type) {
        return engagementRepository.findById(engagementId)
                .filter(Engagement::isRequested)
                .filter(e -> e.getAttendee().getId().equals(authUser.getId()))
                .map(e -> e.reply(type))
                .orElseThrow(() -> new CalendarException(ErrorCode.BAD_REQUEST))
                .getStatus();
    }
}
