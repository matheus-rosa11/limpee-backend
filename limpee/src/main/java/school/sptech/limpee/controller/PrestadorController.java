package school.sptech.limpee.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.limpee.model.Prestador;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("prestador")
public class PrestadorController {
    List<Prestador> prestadores = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Prestador>> listarPrestadores() {
        return prestadores.isEmpty() ?
                ResponseEntity.status(204).build() : ResponseEntity.ok(prestadores);
    }


}
