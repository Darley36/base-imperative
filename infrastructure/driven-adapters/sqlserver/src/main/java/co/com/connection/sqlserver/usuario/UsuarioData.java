package co.com.connection.sqlserver.usuario;

import lombok.*;
import org.hibernate.annotations.Table;


import javax.persistence.Entity;
import javax.persistence.Id;



@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Setter
@Table(appliesTo = "usuario_data")
//@Table(name = "usuarioData")
public class UsuarioData {
    @Id
    private Integer identificador;
    private String nombre;
    private String apellido;
    private String correo;
    private String eleccion;
}
