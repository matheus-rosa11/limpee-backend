package school.sptech.limpee.api.controller.endereco;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.limpee.domain.endereco.Endereco;
import school.sptech.limpee.service.endereco.EnderecoService;
import school.sptech.limpee.service.endereco.dto.EnderecoDTO;
import school.sptech.limpee.service.endereco.dto.EnderecoListagemDTO;
import school.sptech.limpee.service.endereco.dto.EnderecoMapper;

import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
    @Autowired
    EnderecoService enderecoService;

    @SecurityRequirement(name = "Bearer")
    @PostMapping()
    public ResponseEntity<Endereco>criar(@RequestBody EnderecoDTO endereco){
        EnderecoDTO teste = this.enderecoService.save(endereco);
        Endereco endereco1 = EnderecoMapper.of(teste);
        return ResponseEntity.status(201).body(endereco1);
    }
    @SecurityRequirement(name = "Bearer")
    @GetMapping()
    public ResponseEntity<List<EnderecoListagemDTO>> listar(){
        List<EnderecoListagemDTO> enderecos = enderecoService.listar();
        return enderecos.isEmpty() ?
                ResponseEntity.created(null).build() :
                ResponseEntity.ok(enderecos);
    }

}
