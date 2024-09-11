package core.util;

//암호화
public interface Encryptor {
    public String encrypt(String origin);
    boolean isMatch(String origin, String hashed);
}
