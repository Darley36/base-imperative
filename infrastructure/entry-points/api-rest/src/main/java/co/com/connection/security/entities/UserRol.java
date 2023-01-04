package co.com.connection.security.entities;

import lombok.*;

import javax.persistence.Entity;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class UserRol {
    private String username;
    private String rol;
}
