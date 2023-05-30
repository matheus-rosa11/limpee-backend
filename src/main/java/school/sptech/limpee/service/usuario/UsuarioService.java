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
import school.sptech.limpee.domain.especialidade.Especialidade;
import school.sptech.limpee.domain.especializacao.Especializacao;
import school.sptech.limpee.domain.usuario.Usuario;
import school.sptech.limpee.service.especialidade.dto.EspecialidadeCriacaoDto;
import school.sptech.limpee.service.especialidade.dto.EspecialidadeMapper;
import school.sptech.limpee.service.especializacao.dto.EspecializacaoMapper;
import school.sptech.limpee.service.usuario.autenticacao.dto.UsuarioLoginDto;
import school.sptech.limpee.service.usuario.autenticacao.dto.UsuarioTokenDto;
import school.sptech.limpee.service.usuario.dto.UsuarioAvaliacaoDTO;
import school.sptech.limpee.service.usuario.dto.UsuarioCriacaoDto;
import school.sptech.limpee.service.usuario.dto.UsuarioDto;
import school.sptech.limpee.service.usuario.dto.UsuarioMapper;
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

    public List<Usuario> findAllByNomeIgnoreCase(String nome) {
        return usuarioRepository.findAllByNomeIgnoreCase(nome);
    }
    public List<UsuarioAvaliacaoDTO> orderByUsuarioByNotaDesc(){

        return usuarioRepository.getUsuarioOrderByNota();
    }

    public List<UsuarioDto> listar() {
        List<Usuario> usuarios = usuarioRepository.findAll();
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

//    public String gravaArquivoTxt(String nomeArq) {
//        int contaRegDadosGravados = 0;
//        List<Usuario> lista = usuarioRepository.findAll();

//        // Monta o registro de header
//        String header = "00NOTA20231";
//        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
//        header += "01";

//        // Grava o registro de header
//        BufferedWriter saida = null;

//        // Bloco try-catch para abrir o arquivo
//        try {
//            saida = new BufferedWriter(new FileWriter(nomeArq, true));
//        }
//        catch (IOException erro) {
//            System.out.println("Erro ao abrir o arquivo");
//            System.exit(1);
//        }

//        // Bloco try-catch para gravar e fechar o arquivo
//        try {
//            saida.append(header + "\n");
//            saida.close();
//        }
//        catch (IOException erro) {
//            System.out.println("Erro ao gravar no arquivo");
//        }

//        // Monta e grava os registros de dados ou registros de corpo
//        String corpo;
//        for (int i = 0; i < lista.size(); i++) {
//            Usuario u = lista.get(i);
//            corpo = "02";
//            corpo += String.format("%-5.5s", a.getCurso());
//            corpo += String.format("%-8.8s", a.getRa());
//            corpo += String.format("%-50.50s", a.getNome());
//            corpo += String.format("%-40.40s", a.getDisciplina());
//            corpo += String.format("%05.2f", a.getMedia());
//            corpo += String.format("%03d", a.getQtdFalta());
//            gravaRegistro(corpo, nomeArq);
//            contaRegDadosGravados++;
//        }

//        // Monta e grava o registro de trailer
//        String trailer = "01";
//        trailer += String.format("%010d",contaRegDadosGravados);
//        gravaRegistro(trailer, nomeArq);

//    }

//    public void atualizarEspecializacao(long id, List<EspecializacaoCriacaoDto> especializacoesNovas) {
//        if (!usuarioRepository.existsById(id))
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado.");

//        List<Especializacao> especializacoes = especializacaoRepository.findAllByUsuario(id);

//        if (especializacoes.isEmpty())
//            especializacoes = EspecializacaoMapper.of(especializacoesNovas);
//    }
}
