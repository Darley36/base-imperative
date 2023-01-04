package co.com.connection.api;
import co.com.connection.model.usuario.Usuario;
import co.com.ejemplos.prueba.controller.Usuario.UsuarioController;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ApiRest {
//    private final MyUseCase useCase;

    private final UsuarioController usuarioController;

    @PreAuthorize("hasRole('USER')")
    @PostMapping(path = "/path/sql")
    public ResponseEntity<Usuario> saveSql(@RequestBody Usuario usuario) throws Exception {

        var data = usuarioController.guardarUsuario(usuario);
        Integer identificador = data.getIdentificador();
        if (identificador != null){
            return new ResponseEntity<>(data, HttpStatus.OK);
        }
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/path")
    public ResponseEntity<Usuario> findById(@RequestParam("id") Integer id) {

        var usuario = usuarioController.buscarPorId(id);
        if (usuario.getIdentificador() != null){
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        }
        return new ResponseEntity<>(usuario, HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping(path = "/path/mongo")
    public ResponseEntity<Usuario> saveMongo(@RequestBody Usuario usuario) throws Exception {

        var user = usuarioController.guardarUsuarioMongo(usuario);
        if (user.getIdentificador() != null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
    }
}
