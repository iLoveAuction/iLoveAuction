package iLoveAuction.persistencia;

import iLoveAuction.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    /**
     * Busca el usuario por su email.
     *
     * @param email email del usuario.
     * @return retorna el Usuario con ese email.
     */
    Usuario findByEmail(String email);
}