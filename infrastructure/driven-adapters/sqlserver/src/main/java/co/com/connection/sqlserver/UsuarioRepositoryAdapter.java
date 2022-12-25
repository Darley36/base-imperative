package co.com.connection.sqlserver;

import co.com.connection.model.usuario.Usuario;
import co.com.connection.model.usuario.gateways.UsuarioRepository;
import co.com.connection.sqlserver.usuario.mapper.MapperUsuarioSQL;
import co.com.connection.sqlserver.usuario.repository.UsuarioReposiorySQL;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepositoryAdapter implements UsuarioRepository
{
    private final MapperUsuarioSQL mapperUsuarioSQL;
    private final UsuarioReposiorySQL reposiorySQL;

    public UsuarioRepositoryAdapter(MapperUsuarioSQL mapperUsuarioSQL, UsuarioReposiorySQL reposiorySQL) {
        this.mapperUsuarioSQL = mapperUsuarioSQL;
        this.reposiorySQL = reposiorySQL;
    }


    @Override
    public Usuario guardar(Usuario usuario) {
        var employeeData = mapperUsuarioSQL.entityToData(usuario);
        return mapperUsuarioSQL.dataToEntity(reposiorySQL.save(employeeData));
    }

    @Override
    public Usuario buscarPorId(Integer id) {
        return mapperUsuarioSQL.dataToEntity(reposiorySQL.findById(id).orElse(null));
    }
}
