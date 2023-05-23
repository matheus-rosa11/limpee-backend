package school.sptech.limpee.service.admistrador.dto;

import school.sptech.limpee.domain.administrador.Administrador;
import school.sptech.limpee.domain.especializacao.Especializacao;
import school.sptech.limpee.domain.usuario.Usuario;
import school.sptech.limpee.service.especializacao.dto.EspecializacaoMapper;
import school.sptech.limpee.service.usuario.dto.UsuarioCriacaoDto;
import school.sptech.limpee.service.usuario.dto.UsuarioDto;

public class AdministradorMapper {
    public static Administrador of(AdministradorCriacaoDto administradorCriacaoDto) {
        Administrador administrador = new Administrador();
        administrador.setNome(administradorCriacaoDto.getNome());
        administrador.setSenha(administradorCriacaoDto.getSenha());

        return administrador;
    }

    public static AdministradorDto of(Administrador administrador) {
        AdministradorDto administradorDto = new AdministradorDto();

        administradorDto.setNome(administrador.getNome());

        return administradorDto;
    }
}
