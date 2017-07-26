/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ns.torcedor.controller;

import br.com.ns.torcedor.bean.TorcedorBean;
import br.com.ns.torcedor.model.Torcedor;
import br.com.ns.torcedor.model.TorcedorHasCampanhas;
import br.com.ns.torcedor.service.TorcedorHasCampanhaService;
import br.com.ns.torcedor.service.TorcedorService;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Cesar Searlini
 */
@RestController
public class TorcedorController {

    @Autowired
    private TorcedorService torcedorService;
    
    @Autowired
    private TorcedorHasCampanhaService torcedorHasCampanhaService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Torcedor>> getAll() {
        return torcedorService.listAll();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Torcedor> getTorcedor(@PathVariable Long id) {
        return torcedorService.byId(id);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> salvar(@Valid @RequestBody TorcedorBean torcedor) {
        return torcedorService.salvar(torcedor);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@Valid @RequestBody TorcedorBean torcedor) {
        return torcedorService.atualizar(torcedor);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return torcedorService.delete(id);
    }
    
    @RequestMapping(value = "/addCampanha", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> cadastrarCampanha(@RequestBody List<TorcedorHasCampanhas> listTorcedorCampanhas) {
        return torcedorHasCampanhaService.salvarTorcedorCampanhas(listTorcedorCampanhas);
    }
}
