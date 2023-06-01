package school.sptech.limpee.api.controller.especialidade;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.limpee.domain.especialidade.Especialidade;
import school.sptech.limpee.service.especialidade.EspecialidadeService;
import school.sptech.limpee.service.especialidade.dto.EspecialidadeDto;

import java.util.List;

@Tag(name = "Especialidades", description = "Grupo de requisições de Especialidades")
@RestController
@RequestMapping("/especialidade")
@CrossOrigin
public class EspecialidadeController {
    @Autowired
    EspecialidadeService especialidadeService;

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listagem realizada com sucesso."),
            @ApiResponse(responseCode = "204", description = "Listagem realizada com sucesso. A lista está vazia.")
    })

    @SecurityRequirement(name = "Bearer")
    @GetMapping
    @Operation(summary = "Lista todas as especialidades cadastradas")
    public ResponseEntity<List<EspecialidadeDto>> listar() {
        List<EspecialidadeDto> especialidades = especialidadeService.listar();

        return especialidades.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(especialidades);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Especialidade cadastrada com sucesso.")
    })

    @SecurityRequirement(name = "Bearer")
    @PostMapping
    @Operation(summary = "Cadastrar nova especialidade")
    public ResponseEntity<Especialidade> cadastrar(@RequestBody @Valid EspecialidadeDto especialidadeDto) {
        return ResponseEntity.created(null).body(especialidadeService.cadastrar(especialidadeDto));
    }
}
