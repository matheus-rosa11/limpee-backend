package school.sptech.limpee.service.especializacao.dto;

import lombok.*;
import school.sptech.limpee.domain.especialidade.Especialidade;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EspecializacaoDto {
    private long id;
    private Especialidade especialidade;
}
