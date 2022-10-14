package vestie.servey.global.web.argumentresolver.auth;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.build.Plugin;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import vestie.servey.domain.AuthMember;
import vestie.servey.domain.jwt.AuthToken;
import vestie.servey.global.exception.FailedAuthenticationException;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Component
public class AuthMemberArgumentResolver implements HandlerMethodArgumentResolver {

    private String AUTH_TOKEN_HEADER_NAME = "Authorization";
    private String AUTH_TOKEN_HEADER_PREFIX = "Bearer ";

    private final AuthMemberProvider authMemberProvider;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(Auth.class) != null
                && parameter.getParameterType().equals(AuthMember.class);
    }

    @Override
    public AuthMember resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String authTokenValue = webRequest.getHeader(AUTH_TOKEN_HEADER_NAME);

        if(!authTokenValue.startsWith(AUTH_TOKEN_HEADER_PREFIX)) {
            throw new FailedAuthenticationException("Auth Token이 없어 인증할 수 없습니다.");
        }

        String authToken = authTokenValue.replace(AUTH_TOKEN_HEADER_PREFIX, "").trim();

        AuthMember authMember = authMemberProvider.getAuthMember(AuthToken.builder().token(authToken).build());

        return authMember;

//        HttpServletRequest req = (HttpServletRequest) webRequest.getNativeRequest();
//        return req.getHeader("");
    }
}
