package school.sptech.limpee.service.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.limpee.api.configuration.security.jwt.GerenciadorTokenJwt;
import school.sptech.limpee.api.repository.endereco.EnderecoRepository;
import school.sptech.limpee.api.repository.especialidade.EspecialidadeRepository;
import school.sptech.limpee.api.repository.especializacao.EspecializacaoRepository;
import school.sptech.limpee.api.repository.usuario.UsuarioRepository;
import school.sptech.limpee.domain.csv.ListaObj;
import school.sptech.limpee.domain.endereco.Endereco;
import school.sptech.limpee.domain.especialidade.Especialidade;
import school.sptech.limpee.domain.especializacao.Especializacao;
import school.sptech.limpee.domain.usuario.Usuario;
import school.sptech.limpee.service.endereco.dto.EnderecoDTO;
import school.sptech.limpee.service.endereco.dto.EnderecoMapper;
import school.sptech.limpee.service.especialidade.dto.EspecialidadeMapper;
import school.sptech.limpee.service.especializacao.dto.EspecializacaoDto;
import school.sptech.limpee.service.especializacao.dto.EspecializacaoMapper;
import school.sptech.limpee.service.usuario.autenticacao.dto.UsuarioLoginDto;
import school.sptech.limpee.service.usuario.autenticacao.dto.UsuarioTokenDto;
import school.sptech.limpee.service.usuario.dto.UsuarioAvaliacaoDTO;
import school.sptech.limpee.service.usuario.dto.UsuarioCriacaoDto;
import school.sptech.limpee.service.usuario.dto.UsuarioDto;
import school.sptech.limpee.service.usuario.dto.UsuarioMapper;

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
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private EspecialidadeRepository especialidadeRepository;

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
    public UsuarioDto criar(UsuarioCriacaoDto usuarioCriacaoDto) {

        if (!usuarioCriacaoDto.getTipoUsuario().equalsIgnoreCase("cliente") && !usuarioCriacaoDto.getTipoUsuario().equalsIgnoreCase("prestador"))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O tipo de usuário é inválido. Deve ser \"cliente\" ou \"prestador\"");

        if (Objects.isNull(usuarioCriacaoDto.getEnderecoDTO()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O usuário deve obrigatoriamente ter um endereço.");

        final Usuario novoUsuario = UsuarioMapper.of(usuarioCriacaoDto);

        String senhaCriptografada = passwordEncoder.encode(novoUsuario.getSenha());

        novoUsuario.setSenha(senhaCriptografada);

        novoUsuario.setEndereco(enderecoRepository.save(novoUsuario.getEndereco()));

        if (!usuarioCriacaoDto.getEspecialidades().isEmpty()) {
            List<Especializacao> especializacoes = new ArrayList<>();

            for (String e : usuarioCriacaoDto.getEspecialidades()) {
                Especialidade especialidade = especialidadeRepository.save(new Especialidade(e));
                especializacoes.add(new Especializacao(novoUsuario, especialidade));
            }
            novoUsuario.setEspecializacoes(especializacoes);
        }

        if (!novoUsuario.getEspecializacoes().isEmpty()) {
            List<Especializacao> especializacoes = especializacaoRepository.saveAll(novoUsuario.getEspecializacoes());
            novoUsuario.setEspecializacoes(especializacoes);
        }

        usuarioRepository.save(novoUsuario);

        novoUsuario.getEndereco().setUsuario(novoUsuario);

        enderecoRepository.save(novoUsuario.getEndereco());

        return UsuarioMapper.of(novoUsuario);
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

    public List<Usuario> findAllByNomeIgnoreCase(String nome) {
        return usuarioRepository.findAllByNomeIgnoreCase(nome);
    }
    public List<UsuarioAvaliacaoDTO> orderByUsuarioByNotaDesc(){

        return usuarioRepository.getUsuarioOrderByNota();
    }

    public List<UsuarioDto> listar() {
        List<Usuario> usuarios = usuarioRepository.findAll().stream().filter(Usuario::isAprovado).toList();
        List<UsuarioDto> usuariosDto = new ArrayList<>();

        for (Usuario usuario : usuarios) {
//            usuario.getEspecializacoes().add(new Especializacao(usuario, new Especialidade("teste")));
            usuariosDto.add(UsuarioMapper.of(usuario));
        }

        return usuariosDto;
    }

    public List<UsuarioDto> buscarPorNome(String nome) {

        if (nome.isBlank())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O nome do usuário está nulo ou vazio.");

        List<Usuario> usuarios = usuarioRepository.findAllByNomeIgnoreCase(nome);

        return usuarios.stream()
                .map(UsuarioMapper::of)
                .toList();
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

    public List<Usuario> findAllOrderByRanking() {
        return usuarioRepository.findAllByOrderByRankingDesc();
    }

    public List<UsuarioDto> buscarUsuariosNaoAprovados() {
        return usuarioRepository.findAll()
                .stream()
                .filter(usuario -> usuario.getTipoUsuario().equalsIgnoreCase("prestador"))
                .filter(usuario -> !usuario.isAprovado() && !usuario.isRejeitado())
                .map(UsuarioMapper::of)
                .toList();
    }

    public void aprovarUsuario(long idUsuario, boolean isAprovado) {

        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);

        if (usuario.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe o usuário com ID especificado");

        usuario.get().setAprovado(isAprovado);
        usuario.get().setRejeitado(!isAprovado);

        usuarioRepository.save(usuario.get());
    }

    public UsuarioDto editarPerfil(UsuarioDto usuarioDto) {

        if (Objects.isNull(usuarioDto))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O objeto de usuário está nulo.");

        Usuario usuario = usuarioRepository.findById(usuarioDto.getId()).get();

        usuario.setNome(usuarioDto.getNome());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setTelefone(usuarioDto.getTelefone());



        return UsuarioMapper.of(usuarioRepository.saveAndFlush(usuario));
    }

    public UsuarioDto buscaUsuarioPorId(long idUsuario) {
        return UsuarioMapper.of(findById(idUsuario).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "O usuário com o ID especificado não foi encontrado.")
        ));
    }

}
