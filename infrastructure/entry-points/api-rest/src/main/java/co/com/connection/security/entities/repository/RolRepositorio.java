package co.com.connection.security.entities.repository;


import co.com.connection.security.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepositorio extends JpaRepository<Rol, Long> {

    Optional<Rol> findByNombre(String nombre);

    Boolean existsByNombre(String nombre);

}
