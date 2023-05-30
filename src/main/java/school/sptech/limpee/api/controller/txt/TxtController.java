package school.sptech.limpee.api.controller.txt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.limpee.service.usuario.UsuarioService;

@RestController
@RequestMapping("txts")
public class TxtController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public
}
