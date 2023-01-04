package co.com.connection.security.entities.repository;


import co.com.connection.security.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface RolRepositorio extends JpaRepository<Rol, Long> {

    @Query(value = "SELECT r.rol_id, r.nombre from dbo.roles r inner join dbo.usuarios_roles ur on ur.rol_id = r.rol_id where ur.user_id = (?1)", nativeQuery=true)
    Optional<Set<Rol>> roles(Long user_id);

    Optional<Rol> findByNombre(String nombre);

    Boolean existsByNombre(String nombre);

}
