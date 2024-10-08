package calendarproject.core.domain.entity;


import calendarproject.core.domain.Event;
import calendarproject.core.domain.RequestReplyType;
import calendarproject.core.domain.RequestStatus;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import calendarproject.core.util.Period;

@NoArgsConstructor
@Entity
@Table(name = "engagements")
public class Engagement extends BaseEntity {

    @JoinColumn(name = "schedule_id")
    @ManyToOne
    private Schedule schedule;

    @JoinColumn(name = "attendee_id")
    @ManyToOne
    private User attendee;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    public Engagement(Schedule eventSchedule, User attendee) {
        assert eventSchedule.getScheduleType() == ScheduleType.EVENT;
        this.schedule = eventSchedule;
        this.status = RequestStatus.REQUESTED;
        this.attendee = attendee;
    }

    public Event getEvent() {
        return schedule.toEvent();
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public User getAttendee() {
        return attendee;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public boolean isOverlapped(LocalDate date) {
        return this.schedule.isOverlapped(date);
    }

    public boolean isOverlapped(Period period) {
        return this.schedule.isOverlapped(period);
    }
    public boolean isRequested() {
        return this.status == RequestStatus.REQUESTED;
    }

    public Engagement reply(RequestReplyType type) {
        switch (type) {
            case ACCEPT:
                this.status = RequestStatus.ACCEPTED;
                break;
            case REJECT:
                this.status = RequestStatus.REJECTED;
                break;
        }
        return this;
    }
}