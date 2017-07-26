/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ns.campanha.controller;

import br.com.ns.campanha.service.CampanhaService;
import br.com.ns.campanha.model.Campanha;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Cesar Searlini
 */
@RestController
public class CampanhaController {

    @Autowired
    private CampanhaService campanhaService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Campanha>> getAllCampanha() {
        return campanhaService.listAll();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Campanha> getCampaha(@PathVariable Long id) {
        return campanhaService.byId(id);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> salvar(@Valid @RequestBody Campanha campanha) {
        return campanhaService.salvar(campanha);
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> alterar(@Valid @RequestBody Campanha campanha) {
        return campanhaService.salvar(campanha);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return campanhaService.delete(id);
    }

}
