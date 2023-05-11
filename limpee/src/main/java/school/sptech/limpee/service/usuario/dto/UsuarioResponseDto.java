package school.sptech.limpee.service.usuario.dto;

import lombok.Data;
import school.sptech.limpee.domain.especializacao.Especializacao;
import school.sptech.limpee.service.especializacao.dto.EspecializacaoDto;

import java.util.ArrayList;
import java.util.List;

@Data
public class UsuarioResponseDto extends UsuarioDto {
    private List<EspecializacaoDto> especializacoes;

    public UsuarioResponseDto() {
        this.especializacoes = new ArrayList<>();
    }
}
