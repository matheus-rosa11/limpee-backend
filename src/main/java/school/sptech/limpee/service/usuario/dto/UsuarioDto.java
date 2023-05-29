package school.sptech.limpee.service.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import school.sptech.limpee.service.FormularioServico.dto.FormularioServicoDTO;
import school.sptech.limpee.service.endereco.dto.EnderecoDTO;
import school.sptech.limpee.service.especializacao.dto.EspecializacaoDto;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDto {
    private long id;
    private String nome;
    private String tipoUsuario;
    private String email;
    private String genero;
    private String cpf;
    private String rg;
    private String telefone;
    private int ranking;
    private int qtdServicosSolicitados;
    private int qtdServicosFinalizados;
    private int anosExperiencia;
    private EnderecoDTO endereco;
    private List<FormularioServicoDTO> formularios;
    private List<EspecializacaoDto> especializacoes;
    private boolean isAprovado;

    public UsuarioDto() {
        especializacoes = new ArrayList<>();
        formularios = new ArrayList<>();
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

    public int getQtdServicosSolicitados() {
        return qtdServicosSolicitados;
    }

    public void setQtdServicosSolicitados(int qtdServicosSolicitados) {
        this.qtdServicosSolicitados = qtdServicosSolicitados;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public int getAnosExperiencia() {
        return anosExperiencia;
    }

    public void setAnosExperiencia(int anosExperiencia) {
        this.anosExperiencia = anosExperiencia;
    }

    public int getQtdServicosFinalizados() {
        return qtdServicosFinalizados;
    }

    public void setQtdServicosFinalizados(int qtdServicosFinalizados) {
        this.qtdServicosFinalizados = qtdServicosFinalizados;
    }

    public List<EspecializacaoDto> getEspecializacoes() {
        return especializacoes;
    }

    public void setEspecializacoes(List<EspecializacaoDto> especializacoes) {
        this.especializacoes = especializacoes;
    }

    public boolean isAprovado() {
        return isAprovado;
    }

    public void setAprovado(boolean aprovado) {
        isAprovado = aprovado;
    }

    public List<FormularioServicoDTO> getFormularios() {
        return formularios;
    }

    public void setFormularios(List<FormularioServicoDTO> formularios) {
        this.formularios = formularios;
    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDTO enderecoDTO) {
        this.endereco = enderecoDTO;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
