package co.com.connection.sqlserver.usuario.repository;

import co.com.connection.sqlserver.usuario.UsuarioData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioReposiorySQL extends JpaRepository<UsuarioData, Integer> {

    //@Query("SELECT p FROM Person p WHERE LOWER(p.lastName) = LOWER(:lastName)")
    //public List<Person> find(@Param("lastName") String lastName);
}
