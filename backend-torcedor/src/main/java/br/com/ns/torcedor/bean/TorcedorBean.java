package br.com.ns.torcedor.bean;

import br.com.ns.torcedor.model.Torcedor;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Cesar Searlini
 */
public class TorcedorBean {

    private Long id;
    @NotEmpty(message = "Nome é obrigatório.")
    private String nome;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotEmpty(message = "E-mail é obrigatório.")
    private String email;
    @NotNull(message = "Data de Nascimento é obrigatório.")
    private Date dataNascimento;
    @NotNull(message = "Id time é obrigatorio.")
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

    public Torcedor getTorcedor() {
        Torcedor torcedor = new Torcedor();
        torcedor.setId(this.id);
        torcedor.setNome(this.nome);
        torcedor.setDataNascimento(this.dataNascimento);
        torcedor.setEmail(this.email);
        torcedor.setIdTime(this.idTime);
        return torcedor;
    }

}
