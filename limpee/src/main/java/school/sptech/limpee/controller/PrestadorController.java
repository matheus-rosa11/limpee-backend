package school.sptech.limpee.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.limpee.domain.usuario.Prestador;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("prestadores")
public class PrestadorController {
    List<Prestador> prestadores = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Prestador>> listarPrestadores() {
        return prestadores.isEmpty() ?
                ResponseEntity.status(204).build() : ResponseEntity.ok(prestadores);
    }
    @PostMapping
    public ResponseEntity<Prestador> cadastrarPrestador(@RequestBody Prestador prestador){
        if(prestador != null) {
            prestadores.add(prestador);
            return ResponseEntity.status(201).body(prestador);
        }
        return ResponseEntity.status(400).build();
    }

    @GetMapping("{nome}")
    public ResponseEntity<List<Prestador>> listarPrestadorPorNome(@PathVariable String nome){
        List<Prestador> prestadoresListados = new ArrayList<>();
        boolean exists = false;
        for (var p : prestadores) {
            if(p.getNome().equals(nome)){
                prestadoresListados.add(p);
                exists = true;
            }
        }
        return exists ? ResponseEntity.status(201).body(prestadoresListados) :
                ResponseEntity.status(400).build();
    }


}
