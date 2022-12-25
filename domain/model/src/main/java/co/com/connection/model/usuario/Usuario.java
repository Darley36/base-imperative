package co.com.connection.model.usuario;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class Usuario {
    private Integer identificador;
    private String nombre;
    private String apellido;
    private String correo;
    private String eleccion;
}