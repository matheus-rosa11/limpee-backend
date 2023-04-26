package school.sptech.limpee.api.controller.usuario;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import school.sptech.limpee.domain.csv.ListaObj;
import school.sptech.limpee.domain.usuario.Usuario;
import school.sptech.limpee.service.usuario.UsuarioService;
import school.sptech.limpee.service.usuario.autenticacao.dto.UsuarioDetalhesDto;
import school.sptech.limpee.service.usuario.autenticacao.dto.UsuarioLoginDto;
import school.sptech.limpee.service.usuario.autenticacao.dto.UsuarioTokenDto;
import school.sptech.limpee.service.usuario.dto.UsuarioCriacaoDto;
import school.sptech.limpee.service.usuario.dto.UsuarioDto;
import school.sptech.limpee.service.usuario.dto.UsuarioMapper;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Tag(name ="Usuários",description = "Grupo de requisições de Usuários")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário atualizado."),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado.")
    })

    @Operation(summary = "Login")
    @PostMapping("/login")
    public ResponseEntity<UsuarioTokenDto> login(@RequestBody UsuarioLoginDto usuarioLoginDto){
        UsuarioTokenDto usuarioTokenDto = usuarioService.autenticar(usuarioLoginDto);
        return  ResponseEntity.status(200).body(usuarioTokenDto);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cadastro realizado com sucesso."),
            @ApiResponse(responseCode = "409", description = "E-mail já existente.")
    })

    @Operation(summary = "Cadastro de Usuário")
    @PostMapping
    public ResponseEntity<Usuario> criar(@RequestBody @Valid UsuarioCriacaoDto usuarioCriacaoDto) {
        if (usuarioService.existsByEmail(usuarioCriacaoDto.getEmail())) {
            return ResponseEntity.status(409).build();
        }
        Usuario usuario = this.usuarioService.criar(usuarioCriacaoDto);
        return ResponseEntity.status(201).body(usuario);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204",description = "Listagem realizada com sucesso. Não foram encontrados registros de usuário."),
            @ApiResponse(responseCode = "200",description = "Listagem realizada com sucesso."),
            @ApiResponse(responseCode = "401",description = "Não autorizado.")
    })

    @SecurityRequirement(name = "Bearer")
    @Operation(summary = "Lista usuários cadastrados")
    @GetMapping("/lista")
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuario = usuarioService.findAll();

        if (usuario.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(usuario);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso."),
            @ApiResponse(responseCode = "204", description = "Não foram encontrados registros correspondentes.")
    })

    @SecurityRequirement(name = "Bearer")
    @GetMapping("/lista/nome")
    @Operation(summary = "Busca usuários por nome")
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam String nome) {
        List<Usuario> usuarios = usuarioService.findAllByNome(nome);

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
        if (tipoUsuario.isBlank())
            return ResponseEntity.badRequest().build();

        if (!(tipoUsuario.equalsIgnoreCase("cliente") || tipoUsuario.equalsIgnoreCase("prestador")))
            return ResponseEntity.badRequest().build();

        List<Usuario> usuarios = usuarioService.findByTipoUsuarioIgnoreCase(tipoUsuario);

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

        Optional<Usuario> usuario = usuarioService.findById(id);

        if (usuario.isPresent()) {
            usuario.get().setNome(novoUsuario.getNome());
            usuarioService.save(usuario.get());
            return ResponseEntity.ok(UsuarioMapper.of(usuario.get()));
        }

        return ResponseEntity.notFound().build();
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
        List<Usuario> usuarios = usuarioService.findAll();

        ListaObj<Usuario> clienteObj = new ListaObj<>(usuarios.size());

        for (int i = 0; i < usuarios.size(); i++) {
            clienteObj.adiciona(usuarios.get(i));
        }

        usuarioService.gravaArquivoCsv(clienteObj, "ClientesLimpee");
        return ResponseEntity.ok("CSV gerado com sucesso.");
    }
}