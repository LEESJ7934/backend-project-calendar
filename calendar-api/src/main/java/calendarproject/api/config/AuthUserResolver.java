package calendarproject.api.config;


import calendarproject.api.dto.AuthUser;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import static calendarproject.api.service.LoginService.LOGIN_SESSION_KEY;

public class AuthUserResolver implements HandlerMethodArgumentResolver {

    //auth유저 인지를 확인하는 과정
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return AuthUser.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        final Long userId = (Long) webRequest.getAttribute(LOGIN_SESSION_KEY,
                WebRequest.SCOPE_SESSION);
        if (userId != null) {
            return AuthUser.of(userId);
        } else {
            throw new RuntimeException("bad request. no session");
        }
    }
}
