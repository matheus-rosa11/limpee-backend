package school.sptech.limpee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.limpee.domain.Cliente;
import school.sptech.limpee.domain.Login;
import school.sptech.limpee.domain.LoginResponse;
import school.sptech.limpee.service.ClienteService;

import javax.swing.text.html.Option;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("clientes")
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> cadastrar(@RequestBody Cliente cliente){
        clienteService.save(cliente);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listar(@RequestHeader(required = false, defaultValue = "0", name = "quantidade") int quantidade) {

        if (quantidade < 0)
            return ResponseEntity.badRequest().build();

        List<Cliente> clientes = clienteService.findAll();

        if (clientes.isEmpty())
            return ResponseEntity.ok(clientes);

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
        return ResponseEntity.ok(clientes);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody Login login) {
        Optional<Cliente> optionalCliente = clienteService.findByEmailAndSenha(login.getEmail(), login.getSenha());

        return optionalCliente.isPresent() ?
                ResponseEntity.ok(new LoginResponse("teste","teste")) :
                ResponseEntity.notFound().build();
    }
}
