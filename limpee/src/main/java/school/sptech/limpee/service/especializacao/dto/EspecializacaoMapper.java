package school.sptech.limpee.service.especializacao.dto;

import school.sptech.limpee.domain.especialidade.Especializacao;

import java.util.List;

public class EspecializacaoMapper {
    public static EspecializacaoDto of(Especializacao especializacao) {
        EspecializacaoDto e = new EspecializacaoDto();

        e.setId(especializacao.getId());
        e.setUsuario(especializacao.getUsuario());
        e.setEspecialidade(especializacao.getEspecialidade());

        return e;
    }

    public static EspecializacaoRetornoDto of (List<EspecializacaoDto> especializacaoDtoList) {
        EspecializacaoRetornoDto e = new EspecializacaoRetornoDto();

        e.setIdUsuario(especializacaoDtoList.get(0).getUsuario().getId());

        for (EspecializacaoDto espDto : especializacaoDtoList) {
            e.adicionarEspecialidade(espDto.getEspecialidade());
        }

        return e;
    }
}
