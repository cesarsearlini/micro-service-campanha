/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ns.torcedor.service;

import br.com.ns.torcedor.bean.TorcedorBean;
import br.com.ns.torcedor.client.CampanhaClient;
import br.com.ns.torcedor.client.TimeClient;
import br.com.ns.torcedor.model.Torcedor;
import br.com.ns.torcedor.repository.TorcedorHasCampanhaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.ns.torcedor.repository.TorcedorRepository;
import br.com.ns.torcedor.util.XResponseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Cesar Searlini
 */
@Service
public class TorcedorService {

    @Autowired
    private TorcedorRepository torcedorRepository;

    @Autowired
    private TorcedorHasCampanhaRepository torcedorHasCampanhaRepository;

    @Autowired
    private TimeClient timeClient;

    @Autowired
    private CampanhaClient campanhaClient;

    public ResponseEntity<List<Torcedor>> listAll() {
        List<Torcedor> listTime = torcedorRepository.findAll();
        listTime.forEach(t -> {
            t.setTime(timeClient);
            t.setCampanhas(campanhaClient, torcedorHasCampanhaRepository.findByIdTorcedor(t.getId()));
        });
        if (listTime.isEmpty()) {
            return XResponseEntity.noContent("Não existe torcedores cadastrados");
        }
        return XResponseEntity.ok(listTime);
    }

    public ResponseEntity<Torcedor> byId(Long id) {
        Torcedor torcedor = torcedorRepository.findById(id);
        if (torcedor == null) {
            return XResponseEntity.noContent("Torcedor não encontrado");
        }
        torcedor.setTime(timeClient);
        torcedor.setCampanhas(campanhaClient, torcedorHasCampanhaRepository.findByIdTorcedor(torcedor.getId()));
        return XResponseEntity.ok(torcedor);
    }

    public ResponseEntity<Void> salvar(TorcedorBean torcedorBean) {
        try {
            if (torcedorRepository.isEmailCadastrado(torcedorBean.getEmail())) {
                return XResponseEntity.badRequest("Torcedor já cadastrado");
            } else {
                torcedorRepository.save(torcedorBean.getTorcedor());
            }
        } catch (Exception e) {
            return XResponseEntity.badRequest("Não foi possivel salvar o torcedor!");
        }
        return XResponseEntity.accepted("Torcedor salvo com sucesso");
    }

    public ResponseEntity<Void> atualizar(TorcedorBean torcedorBean) {
        try {
            torcedorRepository.save(torcedorBean.getTorcedor());
        } catch (Exception e) {
            return XResponseEntity.badRequest("Não foi possivel salvar o torcedor!");
        }
        return XResponseEntity.accepted("Torcedor salvo com sucesso");
    }

    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            torcedorRepository.delete(id);
        } catch (Exception e) {
            return XResponseEntity.noContent("Não foi possivel deletar o time com ID informado!");
        }
        return XResponseEntity.accepted("Time deletado com sucesso");
    }

}
