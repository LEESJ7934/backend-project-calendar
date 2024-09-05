package core.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class Engagement {

    private Long id;
    private Event event;
    private User attendee;
    private LocalDateTime createdAt;
    private RequestStatus requeststatus;


    public Engagement(Event event, User attendee, LocalDateTime createdAt, RequestStatus requeststatus) {
        this.event = event;
        this.attendee = attendee;
        this.createdAt = createdAt;
        this.requeststatus = requeststatus;
    }
}
