package api.service;


import api.dto.LoginReq;
import api.dto.SignUpReq;
import core.domain.entity.User;
import core.dto.UserCreateReq;
import core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
@RequiredArgsConstructor //서비스에서 많이 사용됨
public class LoginService {

    private final static String LOGIN_SESSION_KEY = "USER_ID";
    private final UserService userService;
    //로그인 생성 수정하는 기능은 아님

    @Transactional
    public void signup(SignUpReq signupReq, HttpSession session){
        //UserService에 Create를 담당(이미 존재하는 경우의 유저 검증은 UserService가 담당
        final User user = userService.create(new UserCreateReq(
                signupReq.getName(),
                signupReq.getEmail(),
                signupReq.getPassword(),
                signupReq.getBirthday()
        ));
        session.setAttribute(LOGIN_SESSION_KEY,user.getId());
    }
    @Transactional
    public void login(LoginReq loginReq, HttpSession session){
        final Long userId = (Long) session.getAttribute(LOGIN_SESSION_KEY);
        if(userId != null){
            return;
        }
        final Optional<User> user = userService.findPwMatchUser(loginReq.getEmail(), loginReq.getPassword());
        if(user.isPresent()){
            session.setAttribute(LOGIN_SESSION_KEY, user.get().getId());
        } else {
            throw new RuntimeException("password or email not match");
        }
    }

    public void logout(HttpSession session) {
        //세션 제거
        session.removeAttribute(LOGIN_SESSION_KEY);
    }

}
