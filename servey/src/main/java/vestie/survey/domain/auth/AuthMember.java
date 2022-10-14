package vestie.survey.domain.auth;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthMember {
    private Long id;
    private String username;
}
