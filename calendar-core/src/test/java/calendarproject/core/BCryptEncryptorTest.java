package calendarproject.core;

import calendarproject.core.util.BCryptEncryptor;
import calendarproject.core.util.Encryptor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BCryptEncryptorTest {

    @Test
    void test() {
        final String origin = "password";
        final Encryptor encryptor = new BCryptEncryptor();
        final String hash = encryptor.encrypt(origin);

        assertTrue(encryptor.isMatch(origin,hash));

        final String origin2 = "password22";
        assertFalse(encryptor.isMatch(origin2,hash));
    }
}
