package school.sptech.limpee.api.controller.avaliacao;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.limpee.domain.avaliacao.Avaliacao;
import school.sptech.limpee.domain.endereco.Endereco;
import school.sptech.limpee.domain.usuario.Usuario;
import school.sptech.limpee.service.FormularioServico.dto.FormularioServicoDTO;
import school.sptech.limpee.service.avaliacao.AvaliacaoService;
import school.sptech.limpee.service.avaliacao.dto.AvaliacaoDTO;
import school.sptech.limpee.service.avaliacao.dto.AvalicaoMapper;
import school.sptech.limpee.service.endereco.dto.EnderecoDTO;
import school.sptech.limpee.service.endereco.dto.EnderecoMapper;
import school.sptech.limpee.service.usuario.UsuarioService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {
    @Autowired
    AvaliacaoService avaliacaoService;
    @Autowired
    UsuarioService usuarioService;

    @SecurityRequirement(name = "Bearer")
    @PostMapping()
    public ResponseEntity<AvaliacaoDTO> criar(@RequestBody AvaliacaoDTO avaliacaoDTO, @RequestParam long idUsuario){
        Optional<Usuario> usuarioOptional = usuarioService.findById(idUsuario);

        if (usuarioOptional.isEmpty())
            return ResponseEntity.notFound().build();

        avaliacaoDTO.setUsuario(usuarioOptional.get().getId());

        AvaliacaoDTO avaliacaoDTO1 = this.avaliacaoService.save(avaliacaoDTO, usuarioOptional.get());
        return ResponseEntity.status(201).body(avaliacaoDTO1);
    }
    @SecurityRequirement(name = "Bearer")
    @GetMapping()
    public ResponseEntity<List<AvaliacaoDTO>> listar(){
        List<AvaliacaoDTO> list = avaliacaoService.findAll();
        return list.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(list);
    }
}
