package school.sptech.limpee.service.avaliacao.dto;

import school.sptech.limpee.domain.avaliacao.Avaliacao;

public class AvalicaoMapper {
    public static Avaliacao of(AvaliacaoDTO avaliacaoDTO){
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setComentario(avaliacaoDTO.getComentario());
        avaliacao.setNota(avaliacaoDTO.getNota());
        avaliacao.setId(avaliacaoDTO.getId());
        return avaliacao;
    }
}
