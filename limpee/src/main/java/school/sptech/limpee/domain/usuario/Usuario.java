package school.sptech.limpee.domain.usuario;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import school.sptech.limpee.domain.FormularioServico.FormularioServico;
import school.sptech.limpee.domain.avaliacao.Avaliacao;
import school.sptech.limpee.domain.endereco.Endereco;
import school.sptech.limpee.domain.especialidade.Especialidade;
import school.sptech.limpee.domain.especializacao.Especializacao;

import java.util.ArrayList;
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
    @OneToOne
    public Endereco endereco;
    @OneToMany(mappedBy = "cliente")
    public List<FormularioServico> formularioCliente;
    @OneToMany(mappedBy = "prestador")
    public List<FormularioServico> formularioPrestador;
    @OneToMany(mappedBy = "usuario")
    private List<Especializacao> especializacoes;
    private boolean isAprovado;

    @OneToMany(mappedBy = "usuario")
    private List<Avaliacao> avaliacoes;

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public Usuario() {
        especializacoes = new ArrayList<>();
        formularioCliente = new ArrayList<>();
        formularioPrestador = new ArrayList<>();
    }

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
        especializacoes = new ArrayList<>();
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

    public List<Especializacao> getEspecializacoes() {
        return especializacoes;
    }

    public void setEspecializacoes(List<Especializacao> especializacoes) {
        this.especializacoes = especializacoes;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<FormularioServico> getFormularioCliente() {
        return formularioCliente;
    }

    public void setFormularioCliente(List<FormularioServico> formularioCliente) {
        this.formularioCliente = formularioCliente;
    }

    public List<FormularioServico> getFormularioPrestador() {
        return formularioPrestador;
    }

    public void setFormularioPrestador(List<FormularioServico> formularioPrestador) {
        this.formularioPrestador = formularioPrestador;
    }

    public boolean isAprovado() {
        return isAprovado;
    }

    public void setAprovado(boolean aprovado) {
        isAprovado = aprovado;
    }
}
