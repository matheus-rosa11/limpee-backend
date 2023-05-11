package school.sptech.limpee.service.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.limpee.api.configuration.security.jwt.GerenciadorTokenJwt;
import school.sptech.limpee.api.repository.especializacao.EspecializacaoRepository;
import school.sptech.limpee.api.repository.usuario.UsuarioRepository;
import school.sptech.limpee.domain.csv.ListaObj;
import school.sptech.limpee.domain.especialidade.Especialidade;
import school.sptech.limpee.domain.especializacao.Especializacao;
import school.sptech.limpee.domain.usuario.Usuario;
import school.sptech.limpee.service.especializacao.EspecializacaoService;
import school.sptech.limpee.service.especializacao.dto.EspecializacaoCriacaoDto;
import school.sptech.limpee.service.especializacao.dto.EspecializacaoDto;
import school.sptech.limpee.service.especializacao.dto.EspecializacaoMapper;
import school.sptech.limpee.service.usuario.autenticacao.dto.UsuarioLoginDto;
import school.sptech.limpee.service.usuario.autenticacao.dto.UsuarioTokenDto;
import school.sptech.limpee.service.usuario.dto.UsuarioCriacaoDto;
import school.sptech.limpee.service.usuario.dto.UsuarioDto;
import school.sptech.limpee.service.usuario.dto.UsuarioMapper;
import school.sptech.limpee.service.usuario.dto.UsuarioResponseDto;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

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
    @Autowired
    private EspecializacaoRepository especializacaoRepository;

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

    public String gravaArquivoCsv(String nomeArq) {
        ListaObj<Usuario> lista = this.ordenarPorRanking();
        FileWriter arq = null;
        Formatter saida = null;
        boolean deuRuim = false;

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
        } catch (FormatterClosedException erro) {
            System.out.println("Houve um erro ao gravar o arquivo CSV: " + erro.getMessage());
            deuRuim = true;

        } finally {
            saida.close();
            try {
                arq.close();
            } catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo: " + erro.getMessage());
                deuRuim = true;
            }

            if (deuRuim) {
                throw new RuntimeException("Houve um erro ao gravar o arquivo CSV.");
            }
        }

        return "CSV gerado com sucesso";
    }

    public ListaObj<Usuario> ordenarPorRanking() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        ListaObj<Usuario> clienteObj = new ListaObj<>(usuarios.size());

        for (Usuario u : usuarios) {
            clienteObj.adiciona(u);
        }

        Usuario aux;

        for (int i = 0; i < clienteObj.getTamanho() - 1; i++) {
            for (int j = i + 1; j < clienteObj.getTamanho(); j++) {
                if (clienteObj.getElemento(j).getRanking() > clienteObj.getElemento(i).getRanking()) {
                    aux = clienteObj.getElemento(i);

                    clienteObj.setElemento(i, clienteObj.getElemento(j));
                    clienteObj.setElemento(j, aux);
                }
            }
        }

        return clienteObj;
    }


//    public UsuarioResponseDto pesquisaBinaria(int ranking) {
//        List<Especializacao> especializacoes = especializacaoRepository.findAll();
//        ListaObj<Usuario> usuarioListaObj = this.ordenarPorRanking();
//
//        Usuario usuario = usuarioListaObj.pesquisaBinaria(ranking, usuarioListaObj);
//
//        UsuarioResponseDto usuarioResponseDto = UsuarioMapper.mapToResponse(usuario);
//
//
//        for (Especializacao especializacao : especializacoes) {
//            if (especializacao.getUsuario().getId().equals(usuario.getId())) {
//
//                EspecializacaoDto especializacaoDto = EspecializacaoMapper.of(especializacao);
//
//                usuarioResponseDto.getEspecializacoes().add(especializacaoDto);
//            }
//        }
//
//        return usuarioResponseDto;
//    }

    public List<Usuario> findAllByNomeIgnoreCase(String nome) {
        return usuarioRepository.findAllByNomeIgnoreCase(nome);
    }

//    public List<UsuarioResponseDto> listar() {
//        List<Usuario> usuarios = usuarioRepository.findAll();
//        List<Especializacao> especializacoes = especializacaoRepository.findAll();
//
//        List<UsuarioResponseDto> listUsuariosResponse = new ArrayList<>();
//
//        for (Usuario usuario : usuarios) {
//
//            UsuarioResponseDto usuarioResponseDto = UsuarioMapper.mapToResponse(usuario);
//
//            for (Especializacao especializacao : especializacoes) {
//                if (especializacao.getUsuario().getId().equals(usuario.getId())) {
//
//                    EspecializacaoDto especializacaoDto = EspecializacaoMapper.of(especializacao);
//
//                    usuarioResponseDto.getEspecializacoes().add(especializacaoDto);
//                }
//            }
//
//            listUsuariosResponse.add(usuarioResponseDto);
//        }
//
//        return listUsuariosResponse;
//    }

    public List<UsuarioDto> listar() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioDto> usuariosDto = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            usuario.getEspecializacoes().add(new Especializacao(usuario, new Especialidade("teste")));
            usuariosDto.add(UsuarioMapper.of(usuario));
        }

        return usuariosDto;
    }

    public List<Usuario> buscarPorNome(String nome) {

        if (nome.isBlank())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O nome do usuário está nulo ou vazio.");

        return usuarioRepository.findAllByNomeIgnoreCase(nome);
    }

    public List<Usuario> buscarPorTipo(String tipoUsuario) {

        if (tipoUsuario.isBlank())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O tipo do usuário está nulo ou vazio.");

        if (!(tipoUsuario.equalsIgnoreCase("cliente") || tipoUsuario.equalsIgnoreCase("prestador")))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O tipo do usuário é inválido. Deve ser \"prestador\" ou \"cliente\".");


        return usuarioRepository.findByTipoUsuarioIgnoreCase(tipoUsuario);
    }

    public UsuarioDto atualizarNome(long id, UsuarioCriacaoDto novoUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if (usuario.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário com id " + id + "não encontrado.");
        }

        usuario.get().setNome(novoUsuario.getNome());
        return UsuarioMapper.of(usuarioRepository.save(usuario.get()));
    }

//    public void atualizarEspecializacao(long id, List<EspecializacaoCriacaoDto> especializacoesNovas) {
//        if (!usuarioRepository.existsById(id))
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado.");
//
//        List<Especializacao> especializacoes = especializacaoRepository.findAllByUsuario(id);
//
//        if (especializacoes.isEmpty())
//            especializacoes = EspecializacaoMapper.of(especializacoesNovas);
//    }
}
