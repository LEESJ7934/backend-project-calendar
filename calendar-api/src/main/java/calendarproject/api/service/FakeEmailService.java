package calendarproject.api.service;

import calendarproject.api.dto.EngagementEmailStuff;
import calendarproject.core.domain.entity.Engagement;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;


@Profile("test")
@Service
public class FakeEmailService implements EmailService {

    @Override
    public void sendEngagement(EngagementEmailStuff stuff) {
        System.out.println(stuff.getProps());
    }

}
