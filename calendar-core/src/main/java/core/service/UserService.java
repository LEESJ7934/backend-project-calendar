package core.service;


import core.domain.entity.User;
import core.domain.entity.repository.UserRepository;
import core.dto.UserCreateReq;
import core.util.Encryptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final Encryptor encryptor;
    private final UserRepository userRepository;

    @Transactional
    public User create(UserCreateReq userCreateReq){
        userRepository.findByEmail(userCreateReq.getEmail())
                .ifPresent(e -> {
                    throw new RuntimeException("user already existed");
                });
        return userRepository.save(new User(
                userCreateReq.getName(),
                userCreateReq.getEmail(),
                encryptor.encrypt(userCreateReq.getPassword()),
                userCreateReq.getBirthday()
        ));
    }

    //패스워드 검증
    @Transactional
    public Optional<User> findPwMatchUser(String email, String password) {
        return userRepository.findByEmail(email)
                .map(user -> encryptor.isMatch(user.getPassword(), password) ? user : null);
    }

}
