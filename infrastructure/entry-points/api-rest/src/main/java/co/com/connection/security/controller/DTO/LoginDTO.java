package co.com.connection.security.controller.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class LoginDTO {
    private String usernameOrEmail;
    private String password;
}
