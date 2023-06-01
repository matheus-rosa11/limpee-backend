package school.sptech.limpee.Service;

import school.sptech.limpee.domain.FormularioServico.FormularioServico;
import school.sptech.limpee.domain.administrador.Administrador;
import school.sptech.limpee.domain.avaliacao.Avaliacao;
import school.sptech.limpee.domain.documento.Documento;
import school.sptech.limpee.domain.endereco.Endereco;
import school.sptech.limpee.domain.especialidade.Especialidade;
import school.sptech.limpee.domain.imagem.Imagem;
import school.sptech.limpee.domain.usuario.Usuario;
import school.sptech.limpee.service.FormularioServico.dto.FormularioServicoDTO;
import school.sptech.limpee.service.admistrador.dto.AdministradorDto;
import school.sptech.limpee.service.avaliacao.dto.AvaliacaoDTO;
import school.sptech.limpee.service.documento.dto.DocumentoDto;
import school.sptech.limpee.service.endereco.dto.EnderecoDTO;
import school.sptech.limpee.service.endereco.dto.EnderecoListagemDTO;
import school.sptech.limpee.service.especialidade.EspecialidadeService;
import school.sptech.limpee.service.especialidade.dto.EspecialidadeDto;
import school.sptech.limpee.service.usuario.dto.UsuarioDto;

import java.util.List;
import java.util.stream.Stream;

public class TesteBuilder {
    private TesteBuilder(){
        throw new IllegalStateException("Classe Utilitária");
    }
    public static Usuario criarUsuario(){
        return new Usuario(1L,"teste","Teste@email.com");
    }
    public static List<Usuario> criarListaUsuarios(){
        return List.of(
                new Usuario(1L,"Teste1","Teste1@Email.com" ),
                new Usuario(2L,"Teste2","Teste2@Email.com"),
                new Usuario(3L,"Teste3","Teste3@Email.com")
        );
    }
    public static List<UsuarioDto> criarListaUsuarios1(){
        return List.of(
                new UsuarioDto(),
                new UsuarioDto(),
                new UsuarioDto()
        );
    }
    public static List<AdministradorDto> criarAdmnistrador(){
        return List.of(
                new AdministradorDto("Teste1"),
        new AdministradorDto("Teste2")
        );
    }
    public static List<Administrador> criarAdmnistrador1(){
        return List.of(
                new Administrador("Teste1","123"),
                new Administrador("Teste2","123")
        );
    }
    public static List<FormularioServicoDTO> criarFormulario(){
        return List.of(
          new FormularioServicoDTO(),
          new FormularioServicoDTO()
        );
    }
    public static List<FormularioServico> criarFormulario1(){
        return List.of(
                new FormularioServico(),
                new FormularioServico()
        );
    }
    public static FormularioServico criarFormularioServico(){
        return new FormularioServico();
    }
    public static FormularioServicoDTO criarFormularioServicoDTO(){
        return new FormularioServicoDTO();
    }
    public static List<Endereco> criarEndereco(){
        return List.of(
          new Endereco(),
          new Endereco()
        );
    }
    public static List<EnderecoListagemDTO> criarEndereco1(){
        return List.of(
                new EnderecoListagemDTO(),
                new EnderecoListagemDTO()
        );
    }
    public static List<AvaliacaoDTO> criarAvaliacaoDTO(){
     return List.of(
             new AvaliacaoDTO(),
             new AvaliacaoDTO()
     );
    }
    public static List<Avaliacao> criarAvaliacao(){
        return List.of(
          new Avaliacao(),
          new Avaliacao()
        );
    }
    public static List<Documento> criarDocumentos(){
        return List.of(
                new Documento(),
                new Documento()
        );
    }
    public static List<DocumentoDto> criarDocumentosDTO(){
        return List.of(
                new DocumentoDto(),
                new DocumentoDto()
        );
    }
   public static List<Imagem> criarImagem(){
        return List.of(
                new Imagem(),
                new Imagem()
        );
   }

   public static List<EspecialidadeDto> criarEspecialidadeDTO(){
        return List.of(
                new EspecialidadeDto(),
                new EspecialidadeDto()
        );
   }
   public static List<Especialidade> criarEspecialidade(){
        return List.of(
                new Especialidade(),
                new Especialidade()
        );
   }

   public static Especialidade cadastrarEspecialidade(){
        return new Especialidade();
   }
    public static EspecialidadeDto cadastrarEspecialidadeDto(){
        return new EspecialidadeDto();
    }
}
