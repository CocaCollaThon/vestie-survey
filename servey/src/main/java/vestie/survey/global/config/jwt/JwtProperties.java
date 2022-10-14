package vestie.survey.global.config.jwt;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;

@RequiredArgsConstructor
@Component
public class JwtProperties {

    @Value("${jwt.secret}")
    public String secretKey;

    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

}
