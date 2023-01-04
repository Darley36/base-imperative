package co.com.connection.security.controller.DTO;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class ErrorDetalles {
    private Date marcaDeTiempo;
    private String mensaje;
    private String detalles;
}