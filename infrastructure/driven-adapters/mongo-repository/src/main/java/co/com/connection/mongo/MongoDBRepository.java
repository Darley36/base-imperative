package co.com.connection.mongo;

import co.com.connection.mongo.usuario.UsuarioData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface MongoDBRepository extends MongoRepository<UsuarioData, Integer> , QueryByExampleExecutor<UsuarioData> {
}
