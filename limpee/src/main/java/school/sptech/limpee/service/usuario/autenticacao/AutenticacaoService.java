package school.sptech.limpee.service.usuario.autenticacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import school.sptech.limpee.domain.usuario.Cliente;
import school.sptech.limpee.api.repository.ClienteRepository;
import school.sptech.limpee.service.usuario.autenticacao.dto.ClienteDetalhesDto;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {
    @Autowired
    private ClienteRepository clienteRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Cliente> clienteOpt = clienteRepository.findByEmail(username);

        if (clienteOpt.isEmpty()) {
            throw new UsernameNotFoundException(String.format("Usuário: %s não encontrado.", username));
        }

        return new ClienteDetalhesDto(clienteOpt.get());
    }
}
