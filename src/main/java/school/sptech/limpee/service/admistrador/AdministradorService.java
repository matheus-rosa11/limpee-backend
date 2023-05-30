package school.sptech.limpee.service.admistrador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.limpee.api.repository.administrador.AdministradorRepository;
import school.sptech.limpee.api.repository.usuario.UsuarioRepository;
import school.sptech.limpee.domain.administrador.Administrador;
import school.sptech.limpee.domain.especialidade.Especialidade;
import school.sptech.limpee.domain.especializacao.Especializacao;
import school.sptech.limpee.domain.usuario.Usuario;
import school.sptech.limpee.service.admistrador.dto.AdministradorDto;
import school.sptech.limpee.service.admistrador.dto.AdministradorMapper;
import school.sptech.limpee.service.usuario.dto.UsuarioDto;
import school.sptech.limpee.service.usuario.dto.UsuarioMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdministradorService {
    @Autowired
    private AdministradorRepository administradorRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    public List<AdministradorDto> listar() {

        List<Administrador> administradores = administradorRepository.findAll();
        List<AdministradorDto> administradoresDto = new ArrayList<>();

        for (Administrador administrador : administradores) {
            administradoresDto.add(AdministradorMapper.of(administrador));
        }

        return administradoresDto;
    }

    public boolean aprovarOuReprovar(long idUsuario, boolean isAprovado) {
        if(isAprovado){
            Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
            if(usuario.isPresent()){
                usuario.get().setAprovado(true);
                usuarioRepository.save(usuario.get());
                return  true;
            }else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado");
            }
        }
        else{
            return false;
        }
    }
}
