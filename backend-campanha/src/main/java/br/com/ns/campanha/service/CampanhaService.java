/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ns.campanha.service;

import br.com.ns.campanha.repository.CampanhaRepository;
import br.com.ns.campanha.model.Campanha;
import br.com.ns.campanha.util.XResponseEntity;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Cesar
 */
@Service
public class CampanhaService {

    @Autowired
    private CampanhaRepository campanhaRepository;

    public ResponseEntity<List<Campanha>> listAll() {
        List<Campanha> listTime = campanhaRepository.queryAllAtiva(new Date());
        if (listTime.isEmpty()) {
            return XResponseEntity.noContent("Não existe campanhas ativas");
        }
        return XResponseEntity.ok(listTime);
    }

    public ResponseEntity<Campanha> byId(Long id) {
        Campanha campanha = campanhaRepository.queryById(id, new Date());
        if (campanha == null) {
            return XResponseEntity.noContent("Não existe campanha ativa para esta Id");
        }
        return XResponseEntity.ok(campanha);
    }

    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            campanhaRepository.delete(id);
        } catch (Exception e) {
            return XResponseEntity.noContent("Não foi possivel deletar a campanha com ID informado!");
        }
        return XResponseEntity.accepted("Campanha deletada com sucesso");
    }

    public ResponseEntity<Void> salvar(Campanha campanha) {
        List<Campanha> listCampanhas = campanhaRepository.queryPeriodo(campanha.getDataInicio(), campanha.getDataTermino());
        ajustaCampanhasSomaDias(listCampanhas, campanha);
        try {
            campanhaRepository.save(campanha);
        } catch (Exception e) {
            return XResponseEntity.noContent("Não foi possivel salvar a campanha!");
        }
        return XResponseEntity.accepted("Campanha salva com sucesso");
    }

    private Campanha ajustaCampanhasSomaDias(List<Campanha> listCampanhas, Campanha campanha) {
        if (campanha == null) {
            return null;
        }
        listCampanhas.forEach(c -> {
            if (!c.getId().equals(campanha.getId())) {
                if (c.getDataTermino().equals(campanha.getDataTermino())) {                    
                    campanha.addDiasTermino(1);                    
                }
                if (c.getDataTermino().before(campanha.getDataTermino())) {
                    c.addDiasTermino(1);
                    campanha.addDiasTermino(1);                    
                    ajustaCampanhasSomaDias(listCampanhas, c);
                }                
            }
        });
        return null;
    }

}
