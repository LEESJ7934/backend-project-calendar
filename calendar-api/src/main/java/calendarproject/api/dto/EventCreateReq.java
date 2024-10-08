package calendarproject.api.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;


@Data
public class EventCreateReq {

    @NotBlank
    private final String title;

    private final String description;

    @NotNull
    private final LocalDateTime startAt;
    @NotNull
    private final LocalDateTime endAt;
    @NotNull
    private final List<Long> attendeeIds;
}
