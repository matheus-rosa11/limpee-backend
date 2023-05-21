package school.sptech.limpee.service.avaliacao.dto;

import school.sptech.limpee.domain.avaliacao.Avaliacao;
import school.sptech.limpee.domain.usuario.Usuario;

public class AvalicaoMapper {
    public static Avaliacao of(AvaliacaoDTO avaliacaoDTO, Usuario usuario){
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setComentario(avaliacaoDTO.getComentario());
        avaliacao.setNota(avaliacaoDTO.getNota());
        avaliacao.setUsuario(usuario);

        return avaliacao;
    }
    public static AvaliacaoDTO of(Avaliacao avaliacao){
        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO();
        avaliacaoDTO.setComentario(avaliacao.getComentario());
        avaliacaoDTO.setNota(avaliacao.getNota());
        avaliacaoDTO.setUsuario(avaliacao.getUsuario().getId());
        return avaliacaoDTO;
    }


}
