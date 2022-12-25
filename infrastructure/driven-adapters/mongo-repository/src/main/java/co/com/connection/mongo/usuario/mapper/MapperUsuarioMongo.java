package co.com.connection.mongo.usuario.mapper;


import co.com.connection.model.usuario.Usuario;
import co.com.connection.mongo.usuario.UsuarioData;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MapperUsuarioMongo {

    private final ObjectMapper mapper;

    public Usuario dataToEntity(UsuarioData usuarioData){
        return mapper.map(usuarioData,Usuario.UsuarioBuilder.class)
                .identificador(usuarioData.getIdentificador())
                .nombre(usuarioData.getNombre())
                .apellido(usuarioData.getApellido())
                .correo(usuarioData.getCorreo())
                .eleccion(usuarioData.getEleccion())
                .build();
    }

    public UsuarioData entityToData(Usuario usuario){
        return mapper.mapBuilder(usuario,UsuarioData.UsuarioDataBuilder.class)
                .build();
    }
}
