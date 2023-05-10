package school.sptech.limpee.domain.usuario;

import jakarta.persistence.*;
import school.sptech.limpee.domain.especializacao.Especializacao;

import java.util.List;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String genero;
    private String tipoUsuario;
    private int qtdServicosSolicitados;
    private int qtdServicosFinalizados;
    private int anosExperiencia;
    private int ranking;

    public Usuario() {}

    public Usuario(String nome, String email, String senha, String genero, int ranking, String tipoUsuario, int qtdServicosSolicitados, int qtdServicosFinalizados, int anosExperiencia) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.genero = genero;
        this.ranking = ranking;
        this.tipoUsuario = tipoUsuario;
        this.qtdServicosSolicitados = qtdServicosSolicitados;
        this.qtdServicosFinalizados = qtdServicosFinalizados;
        this.anosExperiencia = anosExperiencia;
    }

    public double calcularMedia(int qtdServico){
        double notaMedia;
        return notaMedia = (ranking/qtdServico);
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        if (!(tipoUsuario.equalsIgnoreCase("cliente") || tipoUsuario.equalsIgnoreCase("prestador")))
            throw new IllegalArgumentException("Tipo usuário inválido");

        this.tipoUsuario = tipoUsuario;
    }

    public int getQtdServicosSolicitados() {
        return qtdServicosSolicitados;
    }

    public void setQtdServicosSolicitados(int qtdServicosSolicitados) {
        this.qtdServicosSolicitados = qtdServicosSolicitados;
    }

    public int getQtdServicosFinalizados() {
        return qtdServicosFinalizados;
    }

    public void setQtdServicosFinalizados(int qtdServicosFinalizados) {
        this.qtdServicosFinalizados = qtdServicosFinalizados;
    }

    public int getAnosExperiencia() {
        return anosExperiencia;
    }

    public void setAnosExperiencia(int anosExperiencia) {
        this.anosExperiencia = anosExperiencia;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
