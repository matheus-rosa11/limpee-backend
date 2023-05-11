package school.sptech.limpee.service.especialidade.dto;

import school.sptech.limpee.domain.especialidade.Especialidade;

public class EspecialidadeMapper {
    public static Especialidade of(EspecialidadeDto especialidadeDto) {
        Especialidade especialidade = new Especialidade();

        especialidade.setDescricao(especialidadeDto.getDescricao());

        return especialidade;
    }

    public static EspecialidadeDto of(Especialidade especialidade) {
        EspecialidadeDto especialidadeDto = new EspecialidadeDto();

        especialidadeDto.setDescricao(especialidade.getDescricao());

        return especialidadeDto;
    }
}
