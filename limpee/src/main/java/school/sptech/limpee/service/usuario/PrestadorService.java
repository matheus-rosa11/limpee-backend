package school.sptech.limpee.service.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.limpee.domain.usuario.Prestador;
import school.sptech.limpee.api.repository.PrestadorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PrestadorService {
    @Autowired
    PrestadorRepository prestadorRepository;
    public List<Prestador> findAll() {
        return prestadorRepository.findAll();
    }

    public Prestador save(Prestador cliente) {
        return prestadorRepository.save(cliente);
    }

    public List<Prestador> findAllByNome(String nome) {
        return prestadorRepository.findAllByNome(nome);
    }

    public Optional<Prestador> findByEmailAndSenha(String email, String senha) {
        return prestadorRepository.findByEmailAndSenha(email, senha);
    }
}
