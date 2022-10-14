package vestie.servey.domain;

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
