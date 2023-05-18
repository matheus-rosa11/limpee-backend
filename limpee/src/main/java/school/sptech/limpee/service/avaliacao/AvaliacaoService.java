package school.sptech.limpee.service.avaliacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.limpee.api.repository.avaliacao.AvaliacaoRepository;
import school.sptech.limpee.domain.FormularioServico.FormularioServico;
import school.sptech.limpee.domain.avaliacao.Avaliacao;
import school.sptech.limpee.domain.endereco.Endereco;
import school.sptech.limpee.domain.usuario.Usuario;
import school.sptech.limpee.service.FormularioServico.dto.FormularioServicoDTO;
import school.sptech.limpee.service.FormularioServico.dto.FormularioServicoMapper;
import school.sptech.limpee.service.avaliacao.dto.AvaliacaoDTO;
import school.sptech.limpee.service.avaliacao.dto.AvalicaoMapper;
import school.sptech.limpee.service.endereco.dto.EnderecoDTO;
import school.sptech.limpee.service.endereco.dto.EnderecoMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvaliacaoService {
    @Autowired
    AvaliacaoRepository avaliacaoRepository;

    public List<AvaliacaoDTO> findAll() {

        List<Avaliacao> avaliacoes = avaliacaoRepository.findAll();
        List<AvaliacaoDTO> avaliacoesDTO = new ArrayList<>();



        for (Avaliacao f : avaliacoes) {
            avaliacoesDTO.add(AvalicaoMapper.of(f));
        }

        return avaliacoesDTO;


    }

    public AvaliacaoDTO save(AvaliacaoDTO avaliacaoDTO, Usuario usuario) {
        final Avaliacao avaliacao = AvalicaoMapper.of(avaliacaoDTO, usuario);
        return AvalicaoMapper.of(avaliacaoRepository.save(avaliacao));
    }
}
