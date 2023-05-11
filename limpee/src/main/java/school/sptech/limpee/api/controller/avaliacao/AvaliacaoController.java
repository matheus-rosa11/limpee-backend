package school.sptech.limpee.api.controller.avaliacao;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.limpee.domain.avaliacao.Avaliacao;
import school.sptech.limpee.domain.endereco.Endereco;
import school.sptech.limpee.service.avaliacao.AvaliacaoService;
import school.sptech.limpee.service.avaliacao.dto.AvaliacaoDTO;
import school.sptech.limpee.service.avaliacao.dto.AvalicaoMapper;
import school.sptech.limpee.service.endereco.dto.EnderecoDTO;
import school.sptech.limpee.service.endereco.dto.EnderecoMapper;

import java.util.List;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {
    @Autowired
    AvaliacaoService avaliacaoService;

    @SecurityRequirement(name = "Bearer")
    @PostMapping()
    public ResponseEntity<Avaliacao> criar(@RequestBody AvaliacaoDTO avaliacaoDTO){
        AvaliacaoDTO avaliacaoDTO1 = this.avaliacaoService.save(avaliacaoDTO);
        Avaliacao avaliacao = AvalicaoMapper.of(avaliacaoDTO1);
        return ResponseEntity.status(201).body(avaliacao);
    }
    @SecurityRequirement(name = "Bearer")
    @GetMapping()
    public ResponseEntity<List<Avaliacao>> listar(){
        List<Avaliacao> avaliacaos = avaliacaoService.findAll();
        return ResponseEntity.status(200).body(avaliacaos);
    }
}
