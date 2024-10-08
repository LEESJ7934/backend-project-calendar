package calendarproject.api.dto;

import calendarproject.core.domain.RequestReplyType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ReplyEngagementReq {
    @NotNull
    private RequestReplyType type;

    public ReplyEngagementReq(RequestReplyType type) {this.type = type;}

    public RequestReplyType getType() { return type; }
}