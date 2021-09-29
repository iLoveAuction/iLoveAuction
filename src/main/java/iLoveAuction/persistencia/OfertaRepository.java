
package iLoveAuction.persistencia;

import iLoveAuction.model.*;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfertaRepository extends MongoRepository<Oferta, String>{

    /**
     * Busca todas las ofertas relacionadas al id del evento ordenadas por sus cantidades descendentemente.
     * @param idEvento id del evento relacionado.
     * @return lista de ofertas relacionadas.
     */
    List<Oferta> findAllByIdEventoOrderByCantidadDesc(String idEvento);
}
