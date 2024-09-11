package core;

import core.domain.entity.User;
import core.util.Encryptor;

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
