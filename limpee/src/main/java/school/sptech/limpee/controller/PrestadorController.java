package school.sptech.limpee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.limpee.domain.Login;
import school.sptech.limpee.domain.LoginResponse;
import school.sptech.limpee.domain.usuario.Prestador;
import school.sptech.limpee.service.PrestadorService;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("prestadores")
public class PrestadorController {
    @Autowired
    PrestadorService prestadorService;
    
    @PostMapping
    public ResponseEntity<Prestador> cadastrar(@RequestBody Prestador prestador){
        prestadorService.save(prestador);
        return ResponseEntity.status(201).body(prestador);
    }

    @GetMapping
    public ResponseEntity<List<Prestador>> listar(@RequestHeader(required = false, defaultValue = "0", name = "quantidade") int quantidade) {

        if (quantidade < 0)
            return ResponseEntity.badRequest().build();

        List<Prestador> prestadores = prestadorService.findAll();

        if (prestadores.isEmpty())
            return ResponseEntity.noContent().build();

        if (quantidade > 0) {
            prestadores = prestadores.stream()
                    .sorted(Comparator.comparing(Prestador::getRanking).reversed())
                    .limit(quantidade)
                    .toList();
        }

        return ResponseEntity.ok(prestadores);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody Login login) throws Exception {
        Optional<Prestador> optionalPrestador = prestadorService.findByEmailAndSenha(login.getEmail(), login.getSenha());

        if (optionalPrestador.isEmpty())
            throw new Exception(String.format("Usuário %s não encontrado"));


        return ResponseEntity.ok(new LoginResponse(
                optionalPrestador.get().getId(),
                optionalPrestador.get().getNome(),
                "Login realizado com sucesso!",
                "token")
        );
    }

    @GetMapping("/nome")
    public ResponseEntity<List<Prestador>> listarPorNome(@RequestHeader(required = false, defaultValue = "", name = "quantidade") String nome) {

        if (nome.isEmpty() || nome.isBlank())
            return ResponseEntity.badRequest().build();

        List<Prestador> prestadores = prestadorService.findAllByNome(nome);

        return prestadores.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(prestadores);
    }
}
