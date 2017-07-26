/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ns.torcedor.service;

import br.com.ns.torcedor.model.TorcedorHasCampanhas;
import br.com.ns.torcedor.repository.TorcedorHasCampanhaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.ns.torcedor.util.XResponseEntity;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Cesar Searlini
 */
@Service
public class TorcedorHasCampanhaService {

    @Autowired
    private TorcedorHasCampanhaRepository torcedorHasCampanhaRepository;    
    
    public ResponseEntity<Void> salvarTorcedorCampanhas(List<TorcedorHasCampanhas> torcedorCampanhas) {        
        try {
            torcedorHasCampanhaRepository.save(torcedorCampanhas);
        } catch (Exception e) {
            return XResponseEntity.badRequest("Não foi possivel salvar a lista de Campanha!");
        }
        return XResponseEntity.accepted("Lista de Campanhas salva com sucesso");
    }

}
