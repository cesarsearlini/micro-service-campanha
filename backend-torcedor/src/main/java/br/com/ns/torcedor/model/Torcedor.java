package br.com.ns.torcedor.model;

import br.com.ns.torcedor.client.Campanha;
import br.com.ns.torcedor.client.CampanhaClient;
import br.com.ns.torcedor.client.Time;
import br.com.ns.torcedor.client.TimeClient;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Cesar Searlini
 */
@Entity
public class Torcedor implements Serializable {

    @Id
    @SequenceGenerator(name = "torcedor_id_seq", sequenceName = "torcedor_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "torcedor_id_seq")
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(length = 45, unique = true)
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    @JsonIgnore
    private Long idTime;
    @Transient
    private Time time;
    @Transient
    private List<Campanha> campanhas = new ArrayList<>();

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Long getIdTime() {
        return idTime;
    }

    public void setIdTime(Long idTime) {
        this.idTime = idTime;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setTime(TimeClient timeClient) {
        this.time = timeClient.getOne(this.idTime);
    }

    public List<Campanha> getCampanhas() {
        return campanhas;
    }

    public void setCampanhas(List<Campanha> campanhas) {
        this.campanhas = campanhas;
    }

    public void setCampanhas(CampanhaClient campanhaClient, List<TorcedorHasCampanhas> listTorcedorCampanhas) {
        try {
            listTorcedorCampanhas.stream().map((l) -> campanhaClient.getOne(l.getIdCampanha())).forEachOrdered((c) -> {
                this.campanhas.add(c);
            });
        } catch (Exception e) {
        }
    }

}
