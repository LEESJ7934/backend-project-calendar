package calendarproject.api.service;

import calendarproject.api.dto.EngagementEmailStuff;

public interface EmailService {
    void sendEngagement(EngagementEmailStuff stuff);
}

