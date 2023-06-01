package school.sptech.limpee.api.controller.usuario;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.limpee.domain.usuario.Usuario;
import school.sptech.limpee.service.usuario.UsuarioService;
import school.sptech.limpee.service.usuario.autenticacao.dto.UsuarioLoginDto;
import school.sptech.limpee.service.usuario.autenticacao.dto.UsuarioTokenDto;
import school.sptech.limpee.service.usuario.dto.UsuarioAvaliacaoDTO;
import school.sptech.limpee.service.usuario.dto.UsuarioCriacaoDto;
import school.sptech.limpee.service.usuario.dto.UsuarioDto;

import java.util.List;

@Tag(name = "Usuários", description = "Grupo de requisições de Usuários")
@RestController
@RequestMapping("/usuarios")
@CrossOrigin
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário atualizado."),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado.")
    })

    @Operation(summary = "Login")
    @PostMapping("/login")
    public ResponseEntity<UsuarioTokenDto> login(@RequestBody UsuarioLoginDto usuarioLoginDto) {
        UsuarioTokenDto usuarioTokenDto = usuarioService.autenticar(usuarioLoginDto);
        return ResponseEntity.status(200).body(usuarioTokenDto);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cadastro realizado com sucesso."),
            @ApiResponse(responseCode = "409", description = "E-mail já cadastrado.")
    })

    @Operation(summary = "Cadastro de Usuário")
    @PostMapping
    public ResponseEntity<UsuarioDto> criar(@RequestBody @Valid UsuarioCriacaoDto usuarioCriacaoDto) {

        if (usuarioService.existsByEmail(usuarioCriacaoDto.getEmail())) {
            return ResponseEntity.status(409).build();
        }

        UsuarioDto usuarioDto = this.usuarioService.criar(usuarioCriacaoDto);
        return ResponseEntity.created(null).body(usuarioDto);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Listagem realizada com sucesso. Não foram encontrados registros de usuário."),
            @ApiResponse(responseCode = "200", description = "Listagem realizada com sucesso."),
            @ApiResponse(responseCode = "401", description = "Não autorizado.")
    })

    @SecurityRequirement(name = "Bearer")
    @Operation(summary = "Lista usuários cadastrados")
    @GetMapping("/lista")
    public ResponseEntity<List<UsuarioDto>> listar() {

        List<UsuarioDto> usuarios = usuarioService.listar();

        return usuarios.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(usuarios);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso."),
            @ApiResponse(responseCode = "204", description = "Não foram encontrados registros correspondentes.")
    })

    @SecurityRequirement(name = "Bearer")
    @GetMapping("/lista/nome")
    @Operation(summary = "Busca usuários por nome")
    public ResponseEntity<List<UsuarioDto>> buscarPorNome(@RequestParam String nome) {

        List<UsuarioDto> usuarios = usuarioService.buscarPorNome(nome);

        return usuarios.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(usuarios);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso."),
            @ApiResponse(responseCode = "204", description = "Não foram encontrados registros correspondentes.")
    })

    @SecurityRequirement(name = "Bearer")
    @GetMapping("/lista/tipoUsuario")
    @Operation(summary = "Busca usuários com base no seu tipo de usuário")
    public ResponseEntity<List<Usuario>> buscarPorTipo(@RequestParam String tipoUsuario) {

        List<Usuario> usuarios = usuarioService.buscarPorTipo(tipoUsuario);

        return usuarios.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(usuarios);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Atualizado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado.")
    })

    @SecurityRequirement(name = "Bearer")
    @PatchMapping("/nome")
    @Operation(summary = "Atualiza nome de usuário")
    public ResponseEntity<UsuarioDto> atualizarNome(@RequestParam long id, @RequestBody UsuarioCriacaoDto novoUsuario) {
        return ResponseEntity.ok(usuarioService.atualizarNome(id, novoUsuario));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "CSV gerado com sucesso."),
            @ApiResponse(responseCode = "404", description = "O recurso solicitado não foi encontrado."),
            @ApiResponse(responseCode = "500", description = "Houve um erro ao gerar o arquivo CSV.")
    })

    @SecurityRequirement(name = "Bearer")
    @Operation(summary = "Gravar arquivo CSV com dados de usuário")
    @GetMapping("/csv")
    public ResponseEntity<String> gravarCsv() {
        return ResponseEntity.ok(usuarioService.gravaArquivoCsv("ClientesLimpee"));
    }

//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "Pesquisa binária gerada com sucesso."),
//            @ApiResponse(responseCode = "401", description = "Não autorizado.")
//    })
//
//    @SecurityRequirement(name = "Bearer")
//    @Operation(summary = "Realizar pesquisa binária por ranking de usuário")
//    @GetMapping("/pesquisaBinaria/ranking")
//    public ResponseEntity<UsuarioResponseDto> pesquisaBinaria(@RequestParam int ranking) {
//        return ResponseEntity.ok(usuarioService.pesquisaBinaria(ranking));
//    }

//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "Atualização realizada com sucesso."),
//            @ApiResponse(responseCode = "401", description = "Não autorizado."),
//            @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
//    })
//
//    @SecurityRequirement(name = "Bearer")
//    @Operation(summary = "Atualizar especializações de um usuário")
//    @PostMapping("/especializacoes/{id}")
//    public ResponseEntity<UsuarioResponseDto> adicionarEspecializacoes(@PathVariable long id, @RequestParam List<EspecializacaoCriacaoDto> especializacoes) {
//        return ResponseEntity.ok(usuarioService.atualizarEspecializacao(id, especializacoes));
//    }
    @SecurityRequirement(name = "Bearer")
    @Operation(summary = "Lista usuários cadastrados")
    @GetMapping("/lista/ranking")
    public ResponseEntity<List<UsuarioAvaliacaoDTO>> selectNomeOrderByMediaNota() {

        List<UsuarioAvaliacaoDTO> usuarios = usuarioService.orderByUsuarioByNotaDesc();

        return usuarios.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(usuarios);
    }
}
