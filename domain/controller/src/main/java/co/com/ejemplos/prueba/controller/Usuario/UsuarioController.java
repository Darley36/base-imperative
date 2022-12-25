package co.com.ejemplos.prueba.controller.Usuario;

import co.com.connection.model.usuario.Usuario;
import co.com.connection.model.usuario.gateways.UsuarioGatewayMongo;
import co.com.connection.model.usuario.gateways.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioRepository usuarioGateway;
    private final UsuarioGatewayMongo usuarioGatewayMongo;

    public Usuario guardarUsuario(Usuario usuario) throws Exception{
        if (usuario.getIdentificador() == null){
            throw new Exception();
        }
        var data = usuarioGateway.guardar(usuario);
        return data;
    }


    public Usuario buscarPorId(Integer id){
        try {
            return usuarioGateway.buscarPorId(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new Usuario();
        }
    }

    public Usuario guardarUsuarioMongo(Usuario usuario) throws Exception{
        if (usuario.getIdentificador() == null){
            throw new Exception();
        }
        return  usuarioGatewayMongo.createUsuario(usuario);
    }
}
