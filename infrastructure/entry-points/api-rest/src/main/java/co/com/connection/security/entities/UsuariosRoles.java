package co.com.connection.security.entities;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Entity
@Table(name = "usuarios_roles")
public class UsuariosRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    //@ManyToOne
    //@JoinColumn(name = "user_id")
    @Column(name = "user_id")
    private Long idUsuario;

    //@ManyToOne
    //@JoinColumn(name = "rol_id")
    @Column(name = "rol_id")
    private Long idRol;
}
