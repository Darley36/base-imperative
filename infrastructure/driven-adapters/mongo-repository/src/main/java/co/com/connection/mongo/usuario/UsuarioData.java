package co.com.connection.mongo.usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("usuarioData")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class UsuarioData {
    @Id
    private Integer identificador;
    private String nombre;
    private String apellido;
    private String correo;
    private String eleccion;
}
