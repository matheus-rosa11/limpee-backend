package school.sptech.limpee.api.controller.txt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.limpee.domain.endereco.Endereco;
import school.sptech.limpee.domain.txt.Txt;
import school.sptech.limpee.service.endereco.dto.EnderecoDTO;
import school.sptech.limpee.service.endereco.dto.EnderecoTxtDTO;
import school.sptech.limpee.service.txt.TxtService;
import school.sptech.limpee.service.usuario.UsuarioService;
import school.sptech.limpee.service.usuario.dto.UsuarioTxtDto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("txts")
public class TxtController {
    @Autowired
    private TxtService txtService;
    @GetMapping("/import")
    public ResponseEntity<Txt> importArquivoTxt(@RequestParam String nomeArq){
        return ResponseEntity.ok(txtService.importArquivoTxt(nomeArq));
    }
    @GetMapping("/export")
    public ResponseEntity<String> exportArquivoTxt(@RequestParam String nomeArq){
        return ResponseEntity.ok(txtService.exportArquivoTxt(nomeArq));
    }
}
