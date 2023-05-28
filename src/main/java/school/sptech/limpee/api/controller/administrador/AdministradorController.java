package school.sptech.limpee.api.controller.administrador;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.limpee.domain.administrador.Administrador;
import school.sptech.limpee.service.admistrador.AdministradorService;
import school.sptech.limpee.service.admistrador.dto.AdministradorDto;
import school.sptech.limpee.service.usuario.dto.UsuarioDto;

import java.util.List;

@Tag(name = "Administradores", description = "Grupo de requisições de Administradores")
@RestController
@RequestMapping("/administradores")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    @Operation(summary = "Lista administradores cadastrados")
    @GetMapping("/lista")
    public ResponseEntity<List<AdministradorDto>> listar(){
        List<AdministradorDto> administradores = administradorService.listar();

        return administradores.isEmpty() ?
                ResponseEntity.created(null).build() :
                ResponseEntity.ok(administradores);
    }

    @PatchMapping("/{idUsuario}")
    public ResponseEntity<Boolean> autenticar(@PathVariable long idUsuario, @RequestParam boolean isAprovado){
        return ResponseEntity.ok(administradorService.aprovarOuReprovar(idUsuario, isAprovado));
    }
}
