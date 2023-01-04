package co.com.connection.security.controller;


import co.com.connection.security.controller.DTO.JWTAuthResonseDTO;
import co.com.connection.security.controller.DTO.LoginDTO;
import co.com.connection.security.controller.DTO.RegistroDTO;
import co.com.connection.security.entities.Rol;
import co.com.connection.security.entities.User;
import co.com.connection.security.entities.UserRol;
import co.com.connection.security.entities.UsuariosRoles;
import co.com.connection.security.entities.repository.RolRepositorio;
import co.com.connection.security.entities.repository.UsuarioRepositorio;
import co.com.connection.security.entities.repository.UsuariosRolesRepositorio;
import co.com.connection.security.seguridad.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthControlador {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private UsuariosRolesRepositorio usuariosRolesRepositorio;

    @Autowired
    private RolRepositorio rolRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/iniciarSesion")
    public ResponseEntity<JWTAuthResonseDTO> authenticateUser(@RequestBody LoginDTO loginDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        //obtenemos el token del jwtTokenProvider
        String token = jwtTokenProvider.generarToken(authentication);

        return ResponseEntity.ok(new JWTAuthResonseDTO(token));
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario(@RequestBody RegistroDTO registroDTO){
        if(usuarioRepositorio.existsByUsername(registroDTO.getUsername())) {
            return new ResponseEntity<>("Ese nombre de usuario ya existe", HttpStatus.BAD_REQUEST);
        }

        if(usuarioRepositorio.existsByEmail(registroDTO.getEmail())) {
            return new ResponseEntity<>("Ese email de usuario ya existe",HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setNombre(registroDTO.getNombre());
        user.setUsername(registroDTO.getUsername());
        user.setEmail(registroDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registroDTO.getPassword()));

        //Rol roles = rolRepositorio.findByNombre("ROLE_ADMIN").get();
        //user.setRoles(Collections.singleton(roles));

        usuarioRepositorio.save(user);
        return new ResponseEntity<>("Usuario registrado exitosamente",HttpStatus.OK);
    }

    @PostMapping("/asignar")
    public ResponseEntity<?> asignarRol(@RequestBody UserRol userRol){
        if(!usuarioRepositorio.existsByUsername(userRol.getUsername())) {
            return new ResponseEntity<>("El usuario no existe", HttpStatus.BAD_REQUEST);
        }

        if(!rolRepositorio.existsByNombre(userRol.getRol())) {
            return new ResponseEntity<>("Ese Rol no existe",HttpStatus.BAD_REQUEST);
        }

        Rol roles = rolRepositorio.findByNombre(userRol.getRol()).get();
        Optional<User> user = usuarioRepositorio.findByUsername(userRol.getUsername());

        UsuariosRoles usuariosRoles = UsuariosRoles.builder()
                .idRol(roles.getId())
                .idUsuario(user.get().getId())
                .build();

        usuariosRolesRepositorio.save(usuariosRoles);
        return new ResponseEntity<>("rol asignado exitosamente",HttpStatus.OK);
    }
}
