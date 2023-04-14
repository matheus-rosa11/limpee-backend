package school.sptech.limpee.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.limpee.domain.usuario.Cliente;
import school.sptech.limpee.domain.Login;
import school.sptech.limpee.domain.LoginResponse;
import school.sptech.limpee.service.usuario.ClienteService;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Tag(name = "Clientes", description = "Retorna métodos relativos a Clientes.")
@RestController
@RequestMapping("clientes")
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @ApiResponses({
            @ApiResponse(responseCode = "204",
                    description = "Clientes",
                    content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "200", description = "Filmes encontrados")
    })

    @PostMapping
    public ResponseEntity<Object> cadastrar(@RequestBody Cliente cliente) {
        Optional<Cliente> optionalCliente = clienteService.findByEmailAndSenha(cliente.getEmail(), cliente.getSenha());

        if (optionalCliente.isEmpty()) {
            clienteService.save(cliente);
            return ResponseEntity.ok(cliente);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Error Message");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody Login login) throws Exception {
        Optional<Cliente> optionalCliente = clienteService.findByEmailAndSenha(login.getEmail(), login.getSenha());

        if (optionalCliente.isEmpty())
            throw new Exception(String.format("Usuário %s não encontrado", login.getEmail()));


        return ResponseEntity.ok(new LoginResponse(
                optionalCliente.get().getId(),
                optionalCliente.get().getNome(),
                "Login realizado com sucesso!",
                "token")
        );
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listar(@RequestHeader(required = false, defaultValue = "0", name = "quantidade") int quantidade) {

        if (quantidade < 0)
            return ResponseEntity.badRequest().build();

        List<Cliente> clientes = clienteService.findAll();

        if (clientes.isEmpty())
            return ResponseEntity.noContent().build();

        if (quantidade > 0) {
            clientes = clientes.stream()
                    .sorted(Comparator.comparing(Cliente::getRanking).reversed())
                    .limit(quantidade)
                    .toList();
        }

        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/nome")
    public ResponseEntity<List<Cliente>> listarPorNome(@RequestHeader(required = false, defaultValue = "", name = "quantidade") String nome) {

        if (nome.isEmpty() || nome.isBlank())
            return ResponseEntity.badRequest().build();

        List<Cliente> clientes = clienteService.findAllByNome(nome);

        return clientes.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(clientes);
    }
}
