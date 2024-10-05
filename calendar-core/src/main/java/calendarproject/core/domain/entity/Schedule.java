package calendarproject.core.domain.entity;

//Event nofitication task의 데이터들이 존재해야함

import calendarproject.core.domain.Event;
import calendarproject.core.domain.Notification;
import calendarproject.core.domain.Task;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "schedules")
public class Schedule {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String title;
    private String description;

    @JoinColumn(name = "writer_id")
    @ManyToOne
    private User writer;

//    //@OneToMany로 관계를 설정해 줘도 되지만 선호되지 않음--> Schdule엔티티를 가져올때 engagements가 꼭 필요한 것은 아님
//
//    private LocalDateTime createdAt;
//    private List<Engagement> engagements;

    @Enumerated(EnumType.STRING)
    private ScheduleType scheduleType;

    //builder패턴을 사용하면 초기화 표현식이 무시됨
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    //각각의 타입의 스케쥴을 만들어 줘야 할때 필요한 메서드
    public static  Schedule event(String title, String description, LocalDateTime startAt, LocalDateTime endAt, User writer){
        return Schedule.builder()
                .title(title)
                .description(description)
                .startAt(startAt)
                .endAt(endAt)
                .writer(writer)
                .scheduleType(ScheduleType.EVENT)
                .build();
    }

    public static  Schedule task(String title, String description, LocalDateTime taskAt, User writer){
        return Schedule.builder()
                .title(title)
                .description(description)
                .startAt(taskAt)
                .writer(writer)
                .scheduleType(ScheduleType.TASK)
                .build();
    }

    public static  Schedule notification(String title, LocalDateTime notifyAt, User writer){
        return Schedule.builder()
                .title(title)
                .startAt(notifyAt)
                .writer(writer)
                .scheduleType(ScheduleType.NOTIFICATION)
                .build();
    }
    //schedule이 다른 domain객체로 변경되는 메서드 생성
    public Task toTask() {
        return new Task(this);
    }

    public Event toEvent() {
        return new Event(this);
    }

    public Notification toNotification() {
        return new Notification(this);
    }

    public boolean isOverlapped(LocalDate date) {
        return Period.of(this.getStartAt(), this.getEndAt()).isOverlapped(date);
    }
}
