package school.sptech.limpee.service.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import school.sptech.limpee.domain.FormularioServico.FormularioServico;
import school.sptech.limpee.service.FormularioServico.dto.FormularioServicoDTO;
import school.sptech.limpee.service.especializacao.dto.EspecializacaoDto;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDto {
    @Size(min = 2, max = 100)
    private String nome;
    @Email
    @NotBlank
    @Size(min = 6, max = 100)
    private String email;
    @NotBlank
    @Size(min = 6, max = 100)
    private String genero;
    @NotBlank
    private int ranking;
    private int qtdServicosSolicitados;
    private int qtdServicosFinalizados;
    private String tipoUsuario;
    @Min(3)
    private int anosExperiencia;
    private List<FormularioServicoDTO> formularios;
    private List<EspecializacaoDto> especializacoes;

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

    public List<FormularioServicoDTO> getFormularios() {
        return formularios;
    }

    public void setFormularios(List<FormularioServicoDTO> formularios) {
        this.formularios = formularios;
    }
}
