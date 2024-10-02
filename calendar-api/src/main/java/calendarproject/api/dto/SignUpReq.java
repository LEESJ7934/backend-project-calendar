package calendarproject.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;


@Data
public class SignUpReq {
    private final String name;
    private final String email;
    private final String password;
    private final LocalDate birthday;

    @JsonCreator
    public SignUpReq(@JsonProperty("name") String name,
                     @JsonProperty("email") String email,
                     @JsonProperty("password") String password,
                     @JsonProperty("birthday") LocalDate birthday) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
    }
}

