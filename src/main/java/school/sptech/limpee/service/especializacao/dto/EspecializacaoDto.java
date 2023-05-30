package school.sptech.limpee.service.especializacao.dto;

import school.sptech.limpee.domain.especialidade.Especialidade;
import school.sptech.limpee.service.especialidade.dto.EspecialidadeDto;

public class EspecializacaoDto {
    private long id;
    private EspecialidadeDto especialidade;

    public EspecializacaoDto() {
    }

    public EspecializacaoDto(long id, EspecialidadeDto especialidade) {
        this.id = id;
        this.especialidade = especialidade;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public EspecialidadeDto getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(EspecialidadeDto especialidade) {
        this.especialidade = especialidade;
    }
}
