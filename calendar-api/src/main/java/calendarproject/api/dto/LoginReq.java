package calendarproject.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
public class LoginReq {
    private final String email;
    private final String password;

    @JsonCreator
    public LoginReq(@JsonProperty("email") String email,
                    @JsonProperty("password") String password) {
        this.email = email;
        this.password = password;
    }
}
