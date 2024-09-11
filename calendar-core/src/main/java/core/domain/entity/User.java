package core.domain.entity;

import core.util.Encryptor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private LocalDate birthday;
    //코드 중복 줄임
    private LocalDateTime createdAt = LocalDateTime.now();


    public User(String name, String email, String password, LocalDate birthday) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
    }

    //전략 패턴 사용
    public boolean isMatch(Encryptor encryptor, String password) {
        return encryptor.isMatch(password, this.password);
    }
}
