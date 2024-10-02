package calendarproject.api.service;

import calendarproject.core.domain.entity.Engagement;

public interface EmailService {
    void sendEngagement(Engagement e);
}
