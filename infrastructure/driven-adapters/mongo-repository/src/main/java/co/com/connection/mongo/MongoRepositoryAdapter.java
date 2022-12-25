package co.com.connection.mongo;

import co.com.connection.model.usuario.Usuario;
import co.com.connection.model.usuario.gateways.UsuarioGatewayMongo;
import co.com.connection.mongo.helper.AdapterOperations;
import co.com.connection.mongo.usuario.UsuarioData;
import co.com.connection.mongo.usuario.mapper.MapperUsuarioMongo;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MongoRepositoryAdapter extends AdapterOperations<Usuario, UsuarioData, Integer, MongoDBRepository> implements UsuarioGatewayMongo
{

    private final MapperUsuarioMongo mapperUsuarioMongo;

    public MongoRepositoryAdapter(MongoDBRepository repository, ObjectMapper mapper, MapperUsuarioMongo mapperUsuarioMongo) {
        super(repository, mapper, d -> mapper.map(d, Usuario.class));
        this.mapperUsuarioMongo = mapperUsuarioMongo;
    }

    @Override
    public Usuario getUsuario(Integer id) {
        var data = super.findByIdData(id);
        return mapperUsuarioMongo.dataToEntity(data);
    }

    @Override
    public Usuario createUsuario(Usuario usuario) {
        var userData = mapperUsuarioMongo.entityToData(usuario);
        return mapperUsuarioMongo.dataToEntity(repository.save(userData));
    }
}
