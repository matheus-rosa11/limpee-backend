package school.sptech.limpee.api.controller.csv;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.limpee.domain.csv.CsvAgenda;
import school.sptech.limpee.domain.csv.FilaObj;
import school.sptech.limpee.domain.csv.ListaObj;
import school.sptech.limpee.domain.usuario.Usuario;
import school.sptech.limpee.service.csv.CsvService;
import school.sptech.limpee.service.usuario.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("csvs")
public class CsvController {
    @Autowired
    private CsvService csvService;

    @Operation(summary = "Lista a agenda de exportação")
    @GetMapping
    public ResponseEntity<FilaObj<CsvAgenda>> listarAgenda(){
        return ResponseEntity.ok().body(csvService.listarAgenda());
    }
    @Operation(summary = "Adiciona lista de usuario na exportação")
    @PostMapping
    public ResponseEntity<CsvAgenda> agendar(){
        return ResponseEntity.ok().body(csvService.agendar());
    }

    @Operation(summary = "Gravar arquivo CSV com dados de usuário")
    @GetMapping("/export")
    public ResponseEntity<String> gravarCsv() {
        return ResponseEntity.ok(csvService.gravaCsv("Usuarios"));
    }
}
