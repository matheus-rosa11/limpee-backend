package school.sptech.limpee.service.avaliacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.limpee.api.repository.avaliacao.AvaliacaoRepository;
import school.sptech.limpee.domain.avaliacao.Avaliacao;
import school.sptech.limpee.domain.endereco.Endereco;
import school.sptech.limpee.service.avaliacao.dto.AvaliacaoDTO;
import school.sptech.limpee.service.avaliacao.dto.AvalicaoMapper;
import school.sptech.limpee.service.endereco.dto.EnderecoDTO;
import school.sptech.limpee.service.endereco.dto.EnderecoMapper;

import java.util.List;

@Service
public class AvaliacaoService {
    @Autowired
    AvaliacaoRepository avaliacaoRepository;

    public List<Avaliacao> findAll() {

        return avaliacaoRepository.findAll();
    }

    public AvaliacaoDTO save(AvaliacaoDTO avaliacaoDTO) {
        final Avaliacao avaliacao = AvalicaoMapper.of(avaliacaoDTO);
        avaliacaoRepository.save(avaliacao);
        return avaliacaoDTO;
    }
}
