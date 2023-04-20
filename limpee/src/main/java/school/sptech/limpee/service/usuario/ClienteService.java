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
import school.sptech.limpee.domain.usuario.Cliente;
import school.sptech.limpee.api.repository.ClienteRepository;
import school.sptech.limpee.service.usuario.autenticacao.dto.ClienteLoginDto;
import school.sptech.limpee.service.usuario.autenticacao.dto.ClienteTokenDto;
import school.sptech.limpee.service.usuario.dto.ClienteCriacaoDto;
import school.sptech.limpee.service.usuario.dto.ClienteMapper;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;
    @Autowired
    private AuthenticationManager authenticationManager;

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

        String senhaCriptografada = passwordEncoder.encode(novoCliente.getSenha());
        novoCliente.setSenha(senhaCriptografada);
        clienteRepository.save(novoCliente);
    }

    public ClienteTokenDto autenticar(ClienteLoginDto clienteLoginDto) {
        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                clienteLoginDto.getEmail(), clienteLoginDto.getSenha()
        );

        final Authentication authentication = authenticationManager.authenticate(credentials);

        Cliente clienteAutenticado =
                clienteRepository.findByEmail(clienteLoginDto.getEmail())
                        .orElseThrow(
                                () -> new ResponseStatusException(404, "Email do usuário não cadastrado", null)
                        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return ClienteMapper.of(clienteAutenticado, token);
    }
}
