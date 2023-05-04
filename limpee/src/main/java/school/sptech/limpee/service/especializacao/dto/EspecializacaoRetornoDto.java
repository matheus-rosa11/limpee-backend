package school.sptech.limpee.service.especializacao.dto;

import school.sptech.limpee.domain.especialidade.Especialidade;

import java.util.ArrayList;
import java.util.List;

public class EspecializacaoRetornoDto {
    private long idUsuario;
    private List<Especialidade> especialidades;

    public EspecializacaoRetornoDto() {
        this.especialidades = new ArrayList<>();
    }

    public EspecializacaoRetornoDto(long idUsuario, List<Especialidade> especialidades) {
        this.idUsuario = idUsuario;
        this.especialidades = especialidades;
    }

    public List<Especialidade> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<Especialidade> especialidades) {
        this.especialidades = especialidades;
    }

    public void adicionarEspecialidade(Especialidade e) {
        this.especialidades.add(e);
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }
}
