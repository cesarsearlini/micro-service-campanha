/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ns.campanha.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Cesar Searlini
 */
@Entity
public class Campanha implements Serializable {

    @Id
    @SequenceGenerator(name = "campanha_id_seq", sequenceName = "campanha_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "campanha_id_seq")
    private Long id;
    @NotEmpty(message = "Nome da campanha é obrigatório.")
    @Column(nullable = false)
    private String nome;
    @NotNull(message = "Data de Inicio é obrigatório.")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dataInicio;
    @NotNull(message = "Data de Termino é obrigatório.")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dataTermino;
    @NotNull(message = "Id do Time é obrigatório.")
    private Long idTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

    public Long getIdTime() {
        return idTime;
    }

    public void setIdTime(Long idTime) {
        this.idTime = idTime;
    }

    public void addDiasTermino(int quantidadeDias) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataTermino);
        calendar.add(Calendar.DATE, quantidadeDias);
        dataTermino = calendar.getTime();
    }
    
}
