package school.sptech.limpee.api.controller.especializacao;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.convert.Delimiter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.limpee.service.especializacao.EspecializacaoService;
import school.sptech.limpee.service.especializacao.dto.EspecializacaoDto;
import school.sptech.limpee.service.especializacao.dto.EspecializacaoMapper;
import school.sptech.limpee.service.especializacao.dto.EspecializacaoRetornoDto;
import school.sptech.limpee.service.usuario.UsuarioService;
import school.sptech.limpee.service.usuario.dto.UsuarioDto;

import java.util.List;

@RestController
@RequestMapping("especializacoes")
public class EspecializacaoController {

    @Autowired
    private EspecializacaoService especializacaoService;
    @Autowired
    private UsuarioService usuarioService;

    @SecurityRequirement(name = "Bearer")
    @PostMapping("/{idUsuario}")
    public ResponseEntity<EspecializacaoRetornoDto> inserirEspecializacoes(@PathVariable long idUsuario, @Size(max = 3) @RequestParam List<Long> especialidades) {

        if (especialidades.isEmpty() || especialidades.size() > 3 || !usuarioService.existsById(idUsuario))
            return ResponseEntity.badRequest().build();

        List<EspecializacaoDto> especializacoesDto = especializacaoService.adicionarEspecializacoes(especialidades, idUsuario);

        EspecializacaoRetornoDto especializacaoRetornoDto = EspecializacaoMapper.of(especializacoesDto);

        return ResponseEntity.status(201).body(especializacaoRetornoDto);
    }
}
