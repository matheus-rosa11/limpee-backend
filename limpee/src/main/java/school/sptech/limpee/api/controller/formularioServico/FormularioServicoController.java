package school.sptech.limpee.api.controller.formularioServico;

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
    @PostMapping("/{id}")
    public ResponseEntity<FormularioServico> criar(@PathVariable Long id, @RequestBody FormularioServicoDTO formularioServicoDTO){
        Usuario usuario = usuarioService.findById(id).get();
        UsuarioDto usuarioDto = UsuarioMapper.of(usuario);
        formularioServicoDTO.setUsuarioDto(usuarioDto);

        FormularioServicoDTO teste = this.formularioServicoService.save(formularioServicoDTO);
        FormularioServico formularioServico = FormularioServicoMapper.of(teste);
        return ResponseEntity.status(201).body(formularioServico);
    }
    @SecurityRequirement(name = "Bearer")
    @GetMapping()
    public ResponseEntity<List<FormularioServico>> listar(){
        List<FormularioServico> formularioServicos = formularioServicoService.findAll();
        return ResponseEntity.status(200).body(formularioServicos);
    }

}
