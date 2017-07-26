/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ns.time.service;

import br.com.ns.time.model.Time;
import br.com.ns.time.repository.TimeRepository;
import br.com.ns.time.util.XResponseEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Cesar Searlini
 */
@Service
public class TimeService {

    @Autowired
    private TimeRepository timeRepository;

    public ResponseEntity<List<Time>> listAll() {
        List<Time> listTime = timeRepository.findAll();
        if (listTime.isEmpty()) {
            return XResponseEntity.noContent("Não existe times cadastrados");
        }
        return XResponseEntity.ok(listTime);
    }

    public ResponseEntity<Time> byId(Long id) {
        Time time = timeRepository.findById(id);
        if (time == null) {
            return XResponseEntity.noContent("Time não encontrado");
        }
        return XResponseEntity.ok(time);
    }

    public ResponseEntity<Void> salvar(Time time) {
        try {
            timeRepository.save(time);
        } catch (Exception e) {
            return XResponseEntity.badRequest("Não foi possivel salvar o time!");
        }
        return XResponseEntity.accepted("Time salvo com sucesso");
    }

    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            timeRepository.delete(id);
        } catch (Exception e) {
            return XResponseEntity.noContent("Não foi possivel deletar o time com ID informado!");
        }
        return XResponseEntity.accepted("Time deletado com sucesso");
    }

}
