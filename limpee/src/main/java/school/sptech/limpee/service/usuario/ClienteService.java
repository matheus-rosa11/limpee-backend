package school.sptech.limpee.service.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.limpee.domain.usuario.Cliente;
import school.sptech.limpee.repository.ClienteRepository;
import school.sptech.limpee.service.usuario.dto.ClienteCriacaoDto;
import school.sptech.limpee.service.usuario.dto.ClienteMapper;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> findAllByNome(String nome) {
        return clienteRepository.findAllByNome(nome);
    }

    public Optional<Cliente> findByEmailAndSenha(String email, String senha) {
        return clienteRepository.findByEmailAndSenha(email, senha);
    }

    public void criar(ClienteCriacaoDto clienteCriacaoDto) {
        final Cliente novoCliente = ClienteMapper.of(clienteCriacaoDto);
        clienteRepository.save(novoCliente);
    }
}
