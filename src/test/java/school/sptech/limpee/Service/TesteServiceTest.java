package school.sptech.limpee.Service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.limpee.api.repository.administrador.AdministradorRepository;
import school.sptech.limpee.api.repository.avaliacao.AvaliacaoRepository;
import school.sptech.limpee.api.repository.documento.DocumentoRepository;
import school.sptech.limpee.api.repository.endereco.EnderecoRepository;
import school.sptech.limpee.api.repository.especialidade.EspecialidadeRepository;
import school.sptech.limpee.api.repository.formularioServico.FormularioServicoRepository;
import school.sptech.limpee.api.repository.imagem.ImagemRepository;
import school.sptech.limpee.api.repository.notificacao.NotificacaoRepository;
import school.sptech.limpee.api.repository.usuario.UsuarioRepository;
import school.sptech.limpee.domain.FormularioServico.FormularioServico;
import school.sptech.limpee.domain.administrador.Administrador;
import school.sptech.limpee.domain.avaliacao.Avaliacao;
import school.sptech.limpee.domain.documento.Documento;
import school.sptech.limpee.domain.endereco.Endereco;
import school.sptech.limpee.domain.especialidade.Especialidade;
import school.sptech.limpee.domain.imagem.Imagem;
import school.sptech.limpee.domain.notificacao.Notificacao;
import school.sptech.limpee.domain.usuario.Usuario;
import school.sptech.limpee.service.FormularioServico.FormularioServicoService;
import school.sptech.limpee.service.FormularioServico.dto.FormularioServicoDTO;
import school.sptech.limpee.service.admistrador.AdministradorService;
import school.sptech.limpee.service.admistrador.dto.AdministradorDto;
import school.sptech.limpee.service.avaliacao.AvaliacaoService;
import school.sptech.limpee.service.avaliacao.dto.AvaliacaoDTO;
import school.sptech.limpee.service.documento.DocumentoService;
import school.sptech.limpee.service.documento.dto.DocumentoDto;
import school.sptech.limpee.service.endereco.EnderecoService;
import school.sptech.limpee.service.endereco.dto.EnderecoDTO;
import school.sptech.limpee.service.endereco.dto.EnderecoListagemDTO;
import school.sptech.limpee.service.especialidade.EspecialidadeService;
import school.sptech.limpee.service.especialidade.dto.EspecialidadeDto;
import school.sptech.limpee.service.imagem.ImagemService;
import school.sptech.limpee.service.notificacao.NotificacaoService;
import school.sptech.limpee.service.notificacao.dto.NotificacaoClienteDto;
import school.sptech.limpee.service.notificacao.dto.NotificacaoDto;
import school.sptech.limpee.service.usuario.UsuarioService;
import school.sptech.limpee.service.usuario.autenticacao.dto.UsuarioLoginDto;
import school.sptech.limpee.service.usuario.autenticacao.dto.UsuarioTokenDto;
import school.sptech.limpee.service.usuario.dto.UsuarioCriacaoDto;
import school.sptech.limpee.service.usuario.dto.UsuarioDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TesteServiceTest {
    @Mock
    NotificacaoRepository notificacaoRepository;
    @InjectMocks
    NotificacaoService notificacaoService;

    @Mock
    EspecialidadeRepository especialidadeRepository;
    @InjectMocks
    EspecialidadeService especialidadeService;
    @Mock
    ImagemRepository imagemRepository;
    @InjectMocks
    ImagemService imagemService;
    @Mock
    DocumentoRepository documentoRepository;
    @InjectMocks
    DocumentoService documentoService;
    @Mock
    AvaliacaoRepository avaliacaoRepository;
    @InjectMocks
    AvaliacaoService avaliacaoService;

    @Mock
    EnderecoRepository enderecoRepository;
    @InjectMocks
    EnderecoService enderecoService;
    @Mock
    FormularioServicoRepository formularioServicoRepository;
    @InjectMocks
    FormularioServicoService formularioServicoService;
@Mock
    AdministradorRepository administradorRepository;
@InjectMocks
    AdministradorService administradorService;

@Mock
private UsuarioRepository usuarioRepository;
@InjectMocks
private UsuarioService usuarioService;


@Test
@DisplayName("Deve Retornar Usuario quando buscar por id")
void retornarUsuario() {
    Usuario usuario = TesteBuilder.criarUsuario();
    Mockito.when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
    Optional<Usuario> resultado = usuarioService.findById(1L);
    assertNotNull(resultado);
}

@Test
@DisplayName("Deve retornar todos os usuarios")
void listarUsuario(){
    List<Usuario> usuario = TesteBuilder.criarListaUsuarios();
    Mockito.when(usuarioRepository.findAll()).thenReturn(usuario);
    List<Usuario> resultado = usuarioService.findAll();
    assertNotNull(resultado);
}
@Test
@DisplayName("Deve retornar todos os usuarios pelo ranking")
void listarPorRanking(){
    List<Usuario> usuarios = TesteBuilder.criarListaUsuarios();
    Mockito.when(usuarioRepository.findAllByOrderByRankingDesc()).thenReturn(usuarios);
    List<Usuario> resultado = usuarioService.findAllOrderByRanking();
    assertNotNull(resultado);
}
@Test
@DisplayName("Deve retornar todos os usuarios pelo nome")
void listarPorNome(){
    List<Usuario> usuarios = TesteBuilder.criarListaUsuarios();
    Mockito.when(usuarioRepository.findAllByNome("Teste1")).thenReturn(usuarios);
    List<Usuario> resultado = usuarioService.findAllByNome("Teste1");
    assertNotNull(resultado);
}
//@Test
//@DisplayName("Deve Retornar Usuario por tipo usuario")
//void listarUsuarioPorTipo(){
//    List<Usuario> usuarios TesteBuilder.criarListaUsuarios();
//    Mockito.when(usuarioRepository.findByTipoUsuarioIgnoreCase);
//}
@Test
    @DisplayName("Deve salvar o usuario e retornar o usuario salvo")
void salvarUsuario(){
    Usuario usuario = TesteBuilder.criarUsuario();
    Mockito.when(usuarioRepository.save(usuario)).thenReturn(usuario);
    Usuario resultado = usuarioService.save(usuario);
    assertNotNull(resultado);
}
@Test
@DisplayName("Deve listar todos os admnistrados")
void listarAdministradores(){
    List<Administrador> administradors = TesteBuilder.criarAdmnistrador1();
    List<AdministradorDto> administrador = TesteBuilder.criarAdmnistrador();
    Mockito.when(administradorRepository.findAll()).thenReturn(administradors);
    List<AdministradorDto> resultado = administradorService.listar();
    assertNotNull(resultado);
}
@Test
@DisplayName("Deve listar todos os formularios")
void listarFormularios(){
    List<FormularioServicoDTO> formularioServicoDTOS = TesteBuilder.criarFormulario();
    List<FormularioServico> formularioServicos = TesteBuilder.criarFormulario1();
    Mockito.when(formularioServicoRepository.findAll()).thenReturn(formularioServicos);
    List<FormularioServicoDTO> resultado = formularioServicoService.findAll();
    assertNotNull(resultado);
}
@Test
@DisplayName("Deve listar todos os enderecos cadastrados")
void listarEnderecos(){
    List<Endereco> enderecos = TesteBuilder.criarEndereco();
    List<EnderecoListagemDTO> enderecoDTOS = TesteBuilder.criarEndereco1();
    Mockito.when(enderecoRepository.findAll()).thenReturn(enderecos);
    List<Endereco> resultado = enderecoService.findAll();
    assertNotNull(resultado);
}
@Test
@DisplayName("Deve Listar todos as avaliações")
void listarAvalicoes(){
    List<Avaliacao> avaliacaos = TesteBuilder.criarAvaliacao();
    List<AvaliacaoDTO> avaliacaoDTOS = TesteBuilder.criarAvaliacaoDTO();
    Mockito.when(avaliacaoRepository.findAll()).thenReturn(avaliacaos);
    List<AvaliacaoDTO> resultado = avaliacaoService.findAll();
    assertNotNull(resultado);
}
@Test
@DisplayName("Deve listar todos os documentos")
    void listarDocumentos(){
    List<Documento> documentos = TesteBuilder.criarDocumentos();
    List<DocumentoDto> documentoDtos = TesteBuilder.criarDocumentosDTO();
    Mockito.when(documentoRepository.findAll()).thenReturn(documentos);
    Stream<Documento> resultado = documentoService.getAllFiles();
    assertNotNull(resultado);
}
@Test
@DisplayName("Deve listar todas as imagens")
void listarImagens(){
    List<Imagem> imagems = TesteBuilder.criarImagem();
    Mockito.when(imagemRepository.findAll()).thenReturn(imagems);
    List<Imagem> resultado = imagemService.findAll();
    assertNotNull(resultado);
}
@Test
    @DisplayName("Listar todos as especialidades")
    void listarEspecialidades(){
    List<Especialidade> especialidades = TesteBuilder.criarEspecialidade();
    List<EspecialidadeDto> especialidadeDtos = TesteBuilder.criarEspecialidadeDTO();
    Mockito.when(especialidadeRepository.findAll()).thenReturn(especialidades);
    List<EspecialidadeDto> resultado = especialidadeService.listar();
    assertNotNull(resultado);
}
@Test
    @DisplayName("Cadastrar especialidade")
    void cadastrarEspecialidade(){
    Especialidade especialidade = TesteBuilder.cadastrarEspecialidade();
    EspecialidadeDto especialidadeDto = TesteBuilder.cadastrarEspecialidadeDto();
    Mockito.when(especialidadeRepository.save(Mockito.any(Especialidade.class))).thenReturn(especialidade);
    Especialidade resultado = especialidadeService.cadastrar(especialidadeDto);
    assertNotNull(resultado);
}
    @Test
    @DisplayName("Cadastrar especialidade")
    void cadastrarEspecialidade1(){
        Especialidade especialidade = TesteBuilder.cadastrarEspecialidade();
        EspecialidadeDto especialidadeDto = TesteBuilder.cadastrarEspecialidadeDto();
        Mockito.when(especialidadeRepository.save(Mockito.any(Especialidade.class))).thenReturn(especialidade);
        Especialidade resultado = especialidadeService.save(especialidade);
        assertNotNull(resultado);
    }
@Test
    @DisplayName("Cadastrar formulario")
    void cadastrarFormularios(){
    FormularioServico formularioServico = TesteBuilder.criarFormularioServico();
    FormularioServicoDTO formularioServicoDTO = TesteBuilder.criarFormularioServicoDTO();
    List<Usuario> usuario = TesteBuilder.criarListaUsuarios();
    Mockito.when(formularioServicoRepository.save(Mockito.any(FormularioServico.class))).thenReturn(formularioServico);
    FormularioServicoDTO resultado = formularioServicoService.save(formularioServicoDTO, usuario.get(1) ,usuario.get(2));
    assertNotNull(resultado);
}
@Test
    @DisplayName("Buscar notificacao por cliente")
    void buscarNotificacaoCliente(){
    List<Notificacao> notificacaos = TesteBuilder.criarNotificacao();
    List<NotificacaoDto> notificacaoDtos = TesteBuilder.criarNotificacaoDTO();
    List<Usuario> usuarios = TesteBuilder.criarListaUsuarios();
    Mockito.when(notificacaoRepository.findAllByIdCliente(1L)).thenReturn(notificacaos);
    List<NotificacaoClienteDto> resultado = notificacaoService.buscarNotificacoesCliente(1L);
    assertNotNull(resultado);

}
    @Test
    @DisplayName("Deve salvar o usuario e retornar o usuario salvo")
    void salvarUsuario1(){
        UsuarioDto usuario = TesteBuilder.cadastrarUsuario();
        UsuarioCriacaoDto usuario2 = TesteBuilder.cadastrarUsuario1();
        Usuario usuario1 = TesteBuilder.criarUsuario();
        Mockito.when(usuarioRepository.save(usuario1)).thenReturn(usuario1);
        UsuarioDto resultado = usuarioService.criar(usuario2);
        assertNotNull(resultado);
    }
    @Test
    @DisplayName("Deve testar se o token usuario")
    void salvarToken(){
        UsuarioTokenDto usuarioTokenDto = TesteBuilder.cadastrarTokenUsuario();
        Usuario usuario = TesteBuilder.criarUsuario();
        UsuarioLoginDto usuarioLoginDto = TesteBuilder.cadastrarLoginDTO();
        Mockito.when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        UsuarioTokenDto resultado = usuarioService.autenticar(usuarioLoginDto);
        assertNotNull(resultado);
    }

}
