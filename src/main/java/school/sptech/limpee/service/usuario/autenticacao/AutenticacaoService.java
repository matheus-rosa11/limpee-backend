package school.sptech.limpee.service.usuario.autenticacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import school.sptech.limpee.api.repository.usuario.UsuarioRepository;
import school.sptech.limpee.domain.usuario.Usuario;
import school.sptech.limpee.service.usuario.autenticacao.dto.UsuarioDetalhesDto;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> clienteOpt = usuarioRepository.findByEmail(username);

        if (clienteOpt.isEmpty()) {
            throw new UsernameNotFoundException(String.format("Usuário: %s não encontrado.", username));
        }

        return new UsuarioDetalhesDto(clienteOpt.get());
    }
}
