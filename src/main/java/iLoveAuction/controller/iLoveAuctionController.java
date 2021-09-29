package iLoveAuction.controller;

import iLoveAuction.model.Oferta;
import iLoveAuction.model.Subasta;
import iLoveAuction.model.Usuario;
import iLoveAuction.service.iLoveAuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/iloveauction")
public class iLoveAuctionController {

    @Autowired
    iLoveAuctionService service;

    @RequestMapping("/hello")
    public String hello() {
        return "Hello, Api OK";
    }

    @RequestMapping(value="/registrar/usuario", method = RequestMethod.POST)
    public ResponseEntity<?> addNewUser(@RequestBody Usuario newUser){

        try {
            service.registrarUsuario(newUser);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex) {
            Logger.getLogger(iLoveAuctionController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.FORBIDDEN);
        }
    }


    @RequestMapping(value="/crear/subasta", method = RequestMethod.POST)
    public ResponseEntity<?> addNewEvent(@RequestBody Subasta newEvent){

        try {
            service.agregarSubasta(newEvent);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex) {
            Logger.getLogger(iLoveAuctionController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value="/consultar/subastas", method = RequestMethod.GET)
    public ResponseEntity<?> getAllEvents() {
        try {
            return new ResponseEntity<>(service.consultarSubasta(), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            Logger.getLogger(iLoveAuctionController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/registrar/oferta", method = RequestMethod.POST)
    public ResponseEntity<?> addNewOffer(@RequestBody Oferta newOffer) {

        try {
            service.registrarOferta(newOffer);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex) {
            Logger.getLogger(iLoveAuctionController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

        @RequestMapping(value="/usuarioId/{id}", method = RequestMethod.GET)
        public ResponseEntity<?> getUserById(@PathVariable("id") String id ){
            try {
                return new ResponseEntity<>(service.findById(id),HttpStatus.ACCEPTED);
            } catch (Exception e) {
                Logger.getLogger(iLoveAuctionController.class.getName()).log(Level.SEVERE, null, e);
                return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
            }
        }

    @RequestMapping(value="/oferta/{idEvento}", method = RequestMethod.GET)
    public ResponseEntity<?> getOfertasByIdEvento(@PathVariable("idEvento") String idEvento ){
        try {
            return new ResponseEntity<>(service.getOfertasByIdEvento(idEvento),HttpStatus.ACCEPTED);
        } catch (Exception e) {
            Logger.getLogger(iLoveAuctionController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value="/usuario/{email}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserByName(@PathVariable("email") String email ){
        try {
            return new ResponseEntity<>(service.findByEmail(email),HttpStatus.ACCEPTED);
        } catch (Exception e) {
            Logger.getLogger(iLoveAuctionController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    }




