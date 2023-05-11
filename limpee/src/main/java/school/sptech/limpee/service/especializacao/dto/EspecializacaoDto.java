package school.sptech.limpee.service.especializacao.dto;

import lombok.*;
import school.sptech.limpee.domain.especialidade.Especialidade;
import school.sptech.limpee.service.especialidade.dto.EspecialidadeDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EspecializacaoDto {
    private long id;
    private EspecialidadeDto especialidade;
}
