package school.sptech.limpee.domain.endereco;

import jakarta.persistence.*;
import school.sptech.limpee.domain.usuario.Usuario;

@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    public String cep;
    public String complemento;
    public String logradouro;
    public String bairro;



    public Endereco(long id, String cep, String complemento, String logradouro, String bairro, Usuario usuario) {
        this.id = id;
        this.cep = cep;
        this.complemento = complemento;
        this.logradouro = logradouro;
        this.bairro = bairro;

    }

    public Endereco() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

}
