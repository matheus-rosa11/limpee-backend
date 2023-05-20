package school.sptech.limpee.service.endereco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.limpee.api.repository.endereco.EnderecoRepository;
import school.sptech.limpee.domain.endereco.Endereco;
import school.sptech.limpee.domain.usuario.Usuario;
import school.sptech.limpee.service.endereco.dto.EnderecoDTO;
import school.sptech.limpee.service.endereco.dto.EnderecoListagemDTO;
import school.sptech.limpee.service.endereco.dto.EnderecoMapper;

import java.util.List;

@Service
public class EnderecoService {
    @Autowired
   private EnderecoRepository enderecoRepository;

    public List<Endereco> findAll() {

        return enderecoRepository.findAll();
    }
    public EnderecoDTO save(EnderecoDTO enderecoDTO) {
        final Endereco endereco = EnderecoMapper.of(enderecoDTO);
        enderecoRepository.save(endereco);
        return enderecoDTO;
    }

    public List<EnderecoListagemDTO> listar() {
        return enderecoRepository.findAll().stream().map(EnderecoMapper::mapToListagem).toList();
    }
}
