package school.sptech.limpee.service.especializacao.dto;

import school.sptech.limpee.domain.especializacao.Especializacao;
import school.sptech.limpee.service.especialidade.dto.EspecialidadeMapper;

import java.util.ArrayList;
import java.util.List;

public class EspecializacaoMapper {
    public static EspecializacaoDto of(Especializacao especializacao) {
        EspecializacaoDto e = new EspecializacaoDto();

        e.setId(especializacao.getId());
        e.setEspecialidade(EspecialidadeMapper.of(especializacao.getEspecialidade()));

        return e;
    }

    public static Especializacao of(EspecializacaoDto especializacaoDto) {
        Especializacao e = new Especializacao();

        e.setId(especializacaoDto.getId());
        e.setEspecialidade(EspecialidadeMapper.of(especializacaoDto.getEspecialidade()));

        return e;
    }

//    public static List<Especializacao> of(List<EspecializacaoCriacaoDto> especializacaoCriacaoDtos) {
//        List<Especializacao> especializacoes = new ArrayList<>();
//
//        for (EspecializacaoCriacaoDto e : especializacaoCriacaoDtos) {
//            especializacoes.add(EspecializacaoMapper.of(e))
//        }
//    }
//
//    public static Especializacao of(EspecializacaoCriacaoDto especializacaoCriacaoDto) {
//        Especializacao e = new Especializacao();
//
//        e.setEspecialidade();
//        return e;
//    }
}
