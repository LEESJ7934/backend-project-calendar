package core.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@Getter
public class Event {

    private Long id;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String title;
    private String description;
    private User writer;
    private LocalDateTime createdAt;
    private List<Engagement> engagements;


    public Event(LocalDateTime startAt, LocalDateTime endAt, String title, String description, User writer, LocalDateTime createdAt) {
        this.startAt = startAt;
        this.endAt = endAt;
        this.title = title;
        this.description = description;
        this.writer = writer;
        this.createdAt = createdAt;
    }

    //engagement를 이벤트 매개변수에 넣으려 했지만 Engagement 생성자가 event 매개 변수를 필요로 하기 때문에 따로 빼놓음
    public void addEngagement(Engagement engagement) {
        if(engagements == null) {
            engagements = Arrays.asList(engagement);
        } else{
            engagements.add(engagement);
        }
    }
}
