package school.sptech.limpee.api.controller.formularioServico;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.limpee.domain.FormularioServico.FormularioServico;
import school.sptech.limpee.domain.endereco.Endereco;
import school.sptech.limpee.domain.usuario.Usuario;
import school.sptech.limpee.service.FormularioServico.FormularioServicoService;
import school.sptech.limpee.service.FormularioServico.dto.FormularioServicoDTO;
import school.sptech.limpee.service.FormularioServico.dto.FormularioServicoMapper;
import school.sptech.limpee.service.endereco.dto.EnderecoDTO;
import school.sptech.limpee.service.endereco.dto.EnderecoMapper;
import school.sptech.limpee.service.usuario.UsuarioService;
import school.sptech.limpee.service.usuario.dto.UsuarioCriacaoDto;
import school.sptech.limpee.service.usuario.dto.UsuarioDto;
import school.sptech.limpee.service.usuario.dto.UsuarioMapper;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/formulario-servico")
public class FormularioServicoController {
    @Autowired
    FormularioServicoService formularioServicoService;
    @Autowired
    UsuarioService usuarioService;
    @SecurityRequirement(name = "Bearer")
    @PostMapping
    public ResponseEntity<FormularioServicoDTO> criar(@RequestParam Long idCliente, @RequestParam long idPrestador, @RequestBody FormularioServicoDTO formularioServicoDTO){
        Optional<Usuario> cliente = usuarioService.findById(idCliente);
        Optional<Usuario> prestador = usuarioService.findById(idPrestador);

        if (cliente.isEmpty() || prestador.isEmpty())
            return ResponseEntity.notFound().build();

        formularioServicoDTO.setCliente(cliente.get().getId());
        formularioServicoDTO.setPrestador(prestador.get().getId());

        FormularioServicoDTO formularioDto = this.formularioServicoService.save(formularioServicoDTO, cliente.get(), prestador.get());
        return ResponseEntity.status(201).body(formularioDto);
    }
    @SecurityRequirement(name = "Bearer")
    @GetMapping()
    public ResponseEntity<List<FormularioServicoDTO>> listar(){
        List<FormularioServicoDTO> list = formularioServicoService.findAll();
        return list.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(list);
    }

//    @SecurityRequirement(name = "Bearer")
//    @PatchMapping("/valor")
//    public ResponseEntity<FormularioServicoDTO> atualizarValor(@RequestParam long id, @RequestParam double valor) {
//        return ResponseEntity.ok(formularioServicoService.atualizarValor(id, valor));
//    }




}
