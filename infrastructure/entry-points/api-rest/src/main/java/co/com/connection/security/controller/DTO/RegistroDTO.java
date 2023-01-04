package co.com.connection.security.controller.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class RegistroDTO {
    private String nombre;
    private String username;
    private String email;
    private String password;
}

