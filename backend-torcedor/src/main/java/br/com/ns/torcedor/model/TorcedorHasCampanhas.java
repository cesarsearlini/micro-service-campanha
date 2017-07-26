/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ns.torcedor.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 *
 * @author Cesar
 */
@Entity @IdClass(TorcedorHasCampanhas.class)
public class TorcedorHasCampanhas implements Serializable {

    @Id
    @GeneratedValue 
    private Long idTorcedor;
    @Id
    @GeneratedValue
    private Long idCampanha;

    public Long getIdTorcedor() {
        return idTorcedor;
    }

    public void setIdTorcedor(Long idTorcedor) {
        this.idTorcedor = idTorcedor;
    }

    public Long getIdCampanha() {
        return idCampanha;
    }

    public void setIdCampanha(Long idCampanha) {
        this.idCampanha = idCampanha;
    }

}
