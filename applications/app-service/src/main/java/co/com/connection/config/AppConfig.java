package co.com.connection.config;

import co.com.connection.model.usuario.gateways.UsuarioGatewayMongo;
import co.com.connection.model.usuario.gateways.UsuarioRepository;
import co.com.ejemplos.prueba.controller.Usuario.UsuarioController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public UsuarioController usuarioController(UsuarioRepository gateway, UsuarioGatewayMongo usuarioGatewayMongo){
        return new UsuarioController(gateway, usuarioGatewayMongo);
    }
}
