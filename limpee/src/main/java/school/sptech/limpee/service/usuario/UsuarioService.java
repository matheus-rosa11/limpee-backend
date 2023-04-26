package school.sptech.limpee.service.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.limpee.api.configuration.security.jwt.GerenciadorTokenJwt;
import school.sptech.limpee.api.repository.usuario.UsuarioRepository;
import school.sptech.limpee.domain.usuario.Usuario;
import school.sptech.limpee.service.usuario.autenticacao.dto.UsuarioLoginDto;
import school.sptech.limpee.service.usuario.autenticacao.dto.UsuarioTokenDto;
import school.sptech.limpee.service.usuario.dto.UsuarioCriacaoDto;
import school.sptech.limpee.service.usuario.dto.UsuarioMapper;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;
    @Autowired
    private AuthenticationManager authenticationManager;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> findAllByNome(String nome) {
        return usuarioRepository.findAllByNome(nome);
    }

    public Optional<Usuario> findByEmailAndSenha(String email, String senha) {
        return usuarioRepository.findByEmailAndSenha(email, senha);
    }

    // Tem retorno de um
    public Usuario criar(UsuarioCriacaoDto usuarioCriacaoDto) {
        final Usuario novoUsuario = UsuarioMapper.of(usuarioCriacaoDto);
        String senhaCriptografada = passwordEncoder.encode(novoUsuario.getSenha());
        novoUsuario.setSenha(senhaCriptografada);
        usuarioRepository.save(novoUsuario);
        return novoUsuario;
    }

    public UsuarioTokenDto autenticar(UsuarioLoginDto usuarioLoginDto) {
        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuarioLoginDto.getEmail(), usuarioLoginDto.getSenha()
        );

        final Authentication authentication = authenticationManager.authenticate(credentials);

        Usuario usuarioAutenticado =
                usuarioRepository.findByEmail(usuarioLoginDto.getEmail())
                        .orElseThrow(
                                () -> new ResponseStatusException(404, "Email do usuário não cadastrado", null)
                        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return UsuarioMapper.of(usuarioAutenticado, token);
    }

    public List<Usuario> findByTipoUsuarioIgnoreCase(String tipoUsuario) {
        return usuarioRepository.findByTipoUsuarioIgnoreCase(tipoUsuario);
    }

    public boolean existsById(long id) {
        return usuarioRepository.existsById(id);
    }

    public Optional<Usuario> findById(long id) {
        return usuarioRepository.findById(id);
    }

    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }
}
