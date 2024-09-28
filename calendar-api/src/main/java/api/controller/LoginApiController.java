package api.controller;

import api.dto.LoginReq;
import api.dto.SignUpReq;
import api.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class LoginApiController {

    private final LoginService loginService;

    @PostMapping("/api/sign-up")
    public ResponseEntity<Void> login(@RequestBody SignUpReq signUpReq, HttpSession httpSession) {
        loginService.signUp(signUpReq, httpSession);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/api/login")
    public ResponseEntity<Void> login(@RequestBody LoginReq loginReq, HttpSession httpSession) {
        loginService.login(loginReq, httpSession);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/api/logout")
    public ResponseEntity<Void> logout(HttpSession httpSession) {
        loginService.logout(httpSession);
        return ResponseEntity.ok().build();
    }
}
