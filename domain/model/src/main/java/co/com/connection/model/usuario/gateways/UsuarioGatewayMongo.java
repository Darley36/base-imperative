package co.com.connection.model.usuario.gateways;


import co.com.connection.model.usuario.Usuario;

public interface UsuarioGatewayMongo {

    Usuario getUsuario(Integer id);
    Usuario createUsuario(Usuario usuario);
}
