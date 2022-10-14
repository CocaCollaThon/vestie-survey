package vestie.servey.global.web.argumentresolver.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vestie.servey.domain.AuthMember;
import vestie.servey.domain.jwt.AuthToken;
import vestie.servey.global.config.jwt.JwtProperties;

@RequiredArgsConstructor
@Component
public class AuthMemberProvider {

    private final JwtProperties jwtProperties;

    public AuthMember getAuthMember(AuthToken authToken){

        Claims body = Jwts.parser().setSigningKey(jwtProperties.secretKey).parseClaimsJws(authToken.getToken()).getBody();
        return AuthMember.builder()
                .id(body.get("id", Long.class))
                .username(body.get("username", String.class))
                .build();
    }

}
