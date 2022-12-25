package co.com.connection.model.usuario.gateways;

import co.com.connection.model.usuario.Usuario;

public interface UsuarioRepository {
    Usuario guardar(Usuario usuario);
    Usuario buscarPorId(Integer id);
}
