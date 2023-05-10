package school.sptech.limpee.service.usuario.dto;

import school.sptech.limpee.domain.usuario.Usuario;
import school.sptech.limpee.service.usuario.autenticacao.dto.UsuarioDetalhesDto;
import school.sptech.limpee.service.usuario.autenticacao.dto.UsuarioTokenDto;

import javax.naming.directory.InvalidAttributeValueException;
import java.util.ArrayList;

public class UsuarioMapper {
    public static Usuario of(UsuarioCriacaoDto usuarioCriacaoDto) {
        Usuario usuario = new Usuario();

        usuario.setEmail(usuarioCriacaoDto.getEmail());
        usuario.setNome(usuarioCriacaoDto.getNome());
        usuario.setSenha(usuarioCriacaoDto.getSenha());
        usuario.setRanking(usuarioCriacaoDto.getRanking());
        usuario.setQtdServicosSolicitados(usuarioCriacaoDto.getQtdServicosSolicitados());
        usuario.setQtdServicosFinalizados(3);
        usuario.setGenero(usuarioCriacaoDto.getGenero());
        usuario.setTipoUsuario(usuarioCriacaoDto.getTipoUsuario());
        usuario.setAnosExperiencia(10);

        return usuario;
    }

    public static UsuarioTokenDto of (Usuario usuario, String token) {
        UsuarioTokenDto usuarioTokenDto = new UsuarioTokenDto();

        usuarioTokenDto.setUserId(usuario.getId());
        usuarioTokenDto.setEmail(usuario.getEmail());
        usuarioTokenDto.setNome(usuario.getNome());
        usuarioTokenDto.setTipoUsuario(usuario.getTipoUsuario());
        usuarioTokenDto.setToken(token);

        return usuarioTokenDto;
    }

    public static UsuarioDto of(Usuario usuario) {
        UsuarioDto usuarioDto = new UsuarioDto();

        usuarioDto.setEmail(usuario.getEmail());
        usuarioDto.setNome(usuario.getNome());
        usuarioDto.setRanking(usuario.getRanking());
        usuarioDto.setQtdServicosSolicitados(usuario.getQtdServicosSolicitados());
        usuarioDto.setGenero(usuario.getGenero());
        usuarioDto.setTipoUsuario("cliente");

        return usuarioDto;
    }

    public static UsuarioResponseDto mapToResponse(Usuario usuario) {
        UsuarioResponseDto usuarioResponseDto = new UsuarioResponseDto();

        usuarioResponseDto.setEmail(usuario.getEmail());
        usuarioResponseDto.setNome(usuario.getNome());
        usuarioResponseDto.setRanking(usuario.getRanking());
        usuarioResponseDto.setQtdServicosSolicitados(usuario.getQtdServicosSolicitados());
        usuarioResponseDto.setGenero(usuario.getGenero());
        usuarioResponseDto.setTipoUsuario(usuario.getTipoUsuario());

        return usuarioResponseDto;
    }
}
