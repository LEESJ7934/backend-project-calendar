package calendarproject.core;

import calendarproject.core.util.Encryptor;
import calendarproject.core.domain.entity.User;

import java.time.LocalDate;

public class UserTest {

    private final Encryptor alwaysMatchEncryptor = new Encryptor() {
        @Override
        public String encrypt(String origin) {
            return origin;
        }

        @Override
        public boolean isMatch(String origin, String hashed) {
            return true;
        }
    };
    void isMatchTest() {
        final User me = new User("name","email","pw", LocalDate.now());
        me.isMatch(alwaysMatchEncryptor,"aaaaa");
    }
}
