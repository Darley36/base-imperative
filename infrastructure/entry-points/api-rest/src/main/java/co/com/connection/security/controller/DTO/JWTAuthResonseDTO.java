package co.com.connection.security.controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JWTAuthResonseDTO {
    private String tokenDeAcceso;
    private String tipoDeToken = "Bearer";


    public JWTAuthResonseDTO(String tokenDeAcceso) {
        this.tokenDeAcceso = tokenDeAcceso;
    }
}

