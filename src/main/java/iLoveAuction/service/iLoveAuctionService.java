package iLoveAuction.service;


import iLoveAuction.model.Oferta;
import iLoveAuction.model.Subasta;
import iLoveAuction.model.Usuario;
import iLoveAuction.persistencia.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class iLoveAuctionService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    OfertaRepository ofertaRepository;

    @Autowired
    SubastaRepository subastaRepository;

    public void registrarUsuario(Usuario newUser) {
        usuarioRepository.save(newUser);
    }

    public void agregarSubasta(Subasta subasta){
        Subasta evt=subastaRepository.save(subasta);
    }

    public List<Subasta> consultarSubasta(){
       return subastaRepository.findAll();
    }
    public void registrarOferta(Oferta newOffer) throws Exception {

        List<Oferta> ofertas= ofertaRepository.findAllByIdEventoOrderByCantidadDesc(newOffer.getIdEvento());
        if(ofertas.size()==0){
            ofertaRepository.save(newOffer);
        }
        else if(ofertas.get(0).getCantidad()< newOffer.getCantidad()) {
            ofertaRepository.save(newOffer);
        }
        else{
            throw new Exception("La oferta debe ser mayor a la maxima oferta registrada.");
        }
    }

    public List<Oferta> getOfertasByIdEvento(String idEvento) throws Exception {
        List<Oferta> ofertas=ofertaRepository.findAllByIdEventoOrderByCantidadDesc(idEvento);
        if(ofertas.size()==0) throw new Exception("No hay ofertas.");
        return ofertas;
    }

    public Usuario findById(String id) throws ServiceNotFoundException {
        Optional<Usuario> usuario=usuarioRepository.findById(id);
        if(usuario.isPresent()){
            return usuario.get();
        } else {
            throw new ServiceNotFoundException("Not found usuario");
        }
    }

    public Usuario findByEmail(String email) throws ServiceNotFoundException{
        Usuario usuario = usuarioRepository.findByEmail(email);
        if(usuario == null){
            throw new ServiceNotFoundException("Not found usuario");
        } else {
            return usuario;
        }
    }


    public Subasta findEventById(String id) throws ServiceNotFoundException{

           Optional<Subasta> evento = subastaRepository.findById(id);

        if(evento.isPresent()){
            return evento.get();
        } else {
            throw new ServiceNotFoundException("Not found usuario");
        }
    }
}