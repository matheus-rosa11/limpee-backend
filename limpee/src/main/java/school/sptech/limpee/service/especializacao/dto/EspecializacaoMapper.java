package school.sptech.limpee.service.especializacao.dto;

import school.sptech.limpee.domain.especializacao.Especializacao;

public class EspecializacaoMapper {
    public static EspecializacaoDto of(Especializacao especializacao) {
        EspecializacaoDto e = new EspecializacaoDto();

        e.setId(especializacao.getId());
        e.setEspecialidade(especializacao.getEspecialidade());

        return e;
    }
}
