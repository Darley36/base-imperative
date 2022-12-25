package co.com.connection.sqlserver.usuario.mapper;

import co.com.connection.model.usuario.Usuario;
import co.com.connection.sqlserver.usuario.UsuarioData;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MapperUsuarioSQL {

    private final ObjectMapper mapper;

    public Usuario dataToEntity(UsuarioData usuarioData){
        var data = mapper.map(usuarioData,Usuario.UsuarioBuilder.class)
                .identificador(usuarioData.getIdentificador())
                .nombre(usuarioData.getNombre())
                .apellido(usuarioData.getApellido())
                .correo(usuarioData.getCorreo())
                .eleccion(usuarioData.getEleccion())
                .build();

        return data;
    }

    public UsuarioData entityToData(Usuario usuario){
        return mapper.mapBuilder(usuario,UsuarioData.UsuarioDataBuilder.class)
                .build();
    }
}
