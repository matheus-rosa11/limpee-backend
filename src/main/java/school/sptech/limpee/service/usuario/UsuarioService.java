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
import school.sptech.limpee.domain.csv.ListaObj;
import school.sptech.limpee.domain.usuario.Usuario;
import school.sptech.limpee.service.usuario.autenticacao.dto.UsuarioLoginDto;
import school.sptech.limpee.service.usuario.autenticacao.dto.UsuarioTokenDto;
import school.sptech.limpee.service.usuario.dto.UsuarioCriacaoDto;
import school.sptech.limpee.service.usuario.dto.UsuarioDto;
import school.sptech.limpee.service.usuario.dto.UsuarioMapper;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;
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
    public void gravaArquivoCsv(ListaObj<Usuario> lista, String nomeArq) {
        FileWriter arq = null;
        Formatter saida = null;
        Boolean deuRuim = false;

        if (!nomeArq.contains(".csv"))
            nomeArq += ".csv";

        try {
            arq = new FileWriter(nomeArq);
            saida = new Formatter(arq);
        } catch (IOException erro) {
            System.out.println("Houve um erro ao abrir o arquivo CSV: " + erro.getMessage());
        }

        try {
            for (int i = 0; i < lista.getTamanho(); i++) {
                Usuario usuario = lista.getElemento(i);
                saida.format("%d;%S;%S;%S;%s;%d\n", usuario.getId(), usuario.getTipoUsuario(), usuario.getNome(), usuario.getGenero(), usuario.getEmail(), usuario.getRanking());
            }
        } catch (FormatterClosedException erro){
            System.out.println("Houve um erro ao gravar o arquivo CSV: " + erro.getMessage());
            deuRuim = true;

        } finally {
            saida.close();
            try {
                arq.close();
            } catch (IOException erro){
                System.out.println("Erro ao fechar o arquivo: " + erro.getMessage());
                deuRuim = true;
            }

            if (deuRuim){
                throw new RuntimeException("Houve um erro ao gravar o arquivo CSV.");
            }
        }
    }

    public ListaObj<Usuario> ordenarPorRanking() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        ListaObj<Usuario> clienteObj = new ListaObj<>(usuarios.size());

        for (Usuario u : usuarios) {
            clienteObj.adiciona(u);
        }

        Usuario aux;

        for (int i = 0; i < clienteObj.getTamanho() - 1; i++){
            for (int j = i + 1; j < clienteObj.getTamanho(); j++){
                if (clienteObj.getElemento(j).getRanking() > clienteObj.getElemento(i).getRanking()){
                    aux = clienteObj.getElemento(i);

                    clienteObj.setElemento(i, clienteObj.getElemento(j));
                    clienteObj.setElemento(j, aux);
                }
            }
        }

        return clienteObj;
    }


    public Usuario pesquisaBinaria(int ranking) {
        ListaObj<Usuario> usuarioListaObj = this.ordenarPorRanking();
        Usuario usuario = usuarioListaObj.pesquisaBinaria(ranking, usuarioListaObj);

        return usuario;
    }

    public List<Usuario> findAllByNomeIgnoreCase(String nome) {
        return usuarioRepository.findAllByNomeIgnoreCase(nome);
    }
}
