package school.sptech.limpee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.limpee.model.Cliente;
import school.sptech.limpee.repository.ClienteRepository;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }


    public List<Cliente> findFirst4ByOrderByRankingDesc() {
        return clienteRepository.findFirst4ByOrderByRankingDesc();
    }
}
