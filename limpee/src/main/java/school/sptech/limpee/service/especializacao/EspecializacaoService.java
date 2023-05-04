package school.sptech.limpee.service.especializacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.limpee.api.repository.especialidade.EspecialidadeRepository;
import school.sptech.limpee.api.repository.especializacao.EspecializacaoRepository;
import school.sptech.limpee.api.repository.usuario.UsuarioRepository;
import school.sptech.limpee.domain.especialidade.Especialidade;
import school.sptech.limpee.domain.especialidade.Especializacao;
import school.sptech.limpee.domain.usuario.Usuario;
import school.sptech.limpee.service.especializacao.dto.EspecializacaoDto;
import school.sptech.limpee.service.especializacao.dto.EspecializacaoMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EspecializacaoService {
    @Autowired
    private EspecializacaoRepository especializacaoRepository;
    @Autowired
    private EspecialidadeRepository especialidadeRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<EspecializacaoDto> adicionarEspecializacoes(List<Long> idEspecializacoes, long idUsuario) {
        List<EspecializacaoDto> especializacoesDto = new ArrayList<>();
        Usuario usuario = usuarioRepository.findById(idUsuario).get();

        for (Long e : idEspecializacoes) {
            Especialidade especialidade = especialidadeRepository.findById(e).get();

            Especializacao especializacao = especializacaoRepository.save(new Especializacao(usuario, especialidade));

            especializacoesDto.add(EspecializacaoMapper.of(especializacao));
        }

        return especializacoesDto;
    }
}
