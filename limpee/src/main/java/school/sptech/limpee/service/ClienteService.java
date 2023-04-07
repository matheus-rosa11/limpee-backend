package school.sptech.limpee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.limpee.domain.Cliente;
import school.sptech.limpee.repository.ClienteRepository;

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
}
