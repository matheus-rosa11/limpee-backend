package school.sptech.limpee.domain.endereco;

import jakarta.persistence.*;
import school.sptech.limpee.domain.usuario.Usuario;
import school.sptech.limpee.service.endereco.dto.EnderecoDTO;

@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String cep;
    private String complemento;
    private String logradouro;
    private String bairro;
    private int numero;
    private String cidade;
    private String estado;
    @OneToOne
    private Usuario usuario;

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

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
