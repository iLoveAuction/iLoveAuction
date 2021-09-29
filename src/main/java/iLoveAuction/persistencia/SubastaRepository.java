
package iLoveAuction.persistencia;

import iLoveAuction.model.Subasta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubastaRepository extends MongoRepository<Subasta, String>{

    /**
     * Busca las subastas por el usuario relacionado.
     * @param usuario el usuario relacionado
     * @return devuelve la lista de eventos relacionados al usuario.
     */
    List<Subasta> findByUsuario(String usuario);
    
}
