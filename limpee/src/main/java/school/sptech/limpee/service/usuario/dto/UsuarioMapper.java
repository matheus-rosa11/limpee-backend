package school.sptech.limpee.service.usuario.dto;

import school.sptech.limpee.domain.especializacao.Especializacao;
import school.sptech.limpee.domain.usuario.Usuario;
import school.sptech.limpee.service.FormularioServico.dto.FormularioServicoDTO;
import school.sptech.limpee.service.FormularioServico.dto.FormularioServicoMapper;
import school.sptech.limpee.service.endereco.dto.EnderecoMapper;
import school.sptech.limpee.service.especializacao.dto.EspecializacaoMapper;
import school.sptech.limpee.service.usuario.autenticacao.dto.UsuarioTokenDto;

import java.util.ArrayList;
import java.util.List;

public class UsuarioMapper {
    public static Usuario of(UsuarioCriacaoDto usuarioCriacaoDto) {
        Usuario usuario = new Usuario();

        usuario.setEmail(usuarioCriacaoDto.getEmail());
        usuario.setNome(usuarioCriacaoDto.getNome());
        usuario.setSenha(usuarioCriacaoDto.getSenha());
        usuario.setGenero(usuarioCriacaoDto.getGenero());
        usuario.setTipoUsuario(usuarioCriacaoDto.getTipoUsuario());
        usuario.setEndereco(EnderecoMapper.of(usuarioCriacaoDto.getEnderecoDTO()));

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
        usuarioDto.setQtdServicosFinalizados(usuario.getQtdServicosFinalizados());
        usuarioDto.setGenero(usuario.getGenero());
        usuarioDto.setTipoUsuario(usuario.getTipoUsuario());
        usuarioDto.setEndereco(EnderecoMapper.of(usuario.getEndereco()));

        List<FormularioServicoDTO> formulariosPrestadorDto = usuario.getFormularioPrestador()
                .stream()
                .map(FormularioServicoMapper::of)
                .toList();

        List<FormularioServicoDTO> formulariosClienteDto = usuario.getFormularioCliente()
                .stream()
                .map(FormularioServicoMapper::of)
                .toList();

        if (usuario.getTipoUsuario().equals("prestador"))
            usuarioDto.setFormularios(formulariosPrestadorDto);
        else if (usuario.getTipoUsuario().equals("cliente"))
            usuarioDto.setFormularios(formulariosClienteDto);
        else {
            usuarioDto.setFormularios(new ArrayList<>());
            throw new IllegalStateException("Tipo de usuário inválido");
        }

        if (!usuario.getEspecializacoes().isEmpty()) {
            for (Especializacao e : usuario.getEspecializacoes()) {
                usuarioDto.getEspecializacoes().add(EspecializacaoMapper.of(e));
            }
        }

        return usuarioDto;
    }

//    public static UsuarioResponseDto mapToResponse(Usuario usuario) {
//        UsuarioResponseDto usuarioResponseDto = new UsuarioResponseDto();
//
//        usuarioResponseDto.setEmail(usuario.getEmail());
//        usuarioResponseDto.setNome(usuario.getNome());
//        usuarioResponseDto.setRanking(usuario.getRanking());
//        usuarioResponseDto.setQtdServicosSolicitados(usuario.getQtdServicosSolicitados());
//        usuarioResponseDto.setGenero(usuario.getGenero());
//        usuarioResponseDto.setTipoUsuario(usuario.getTipoUsuario());
//
//        return usuarioResponseDto;
//    }

    public static Usuario of(UsuarioDto usuarioDto) {
        Usuario u = new Usuario();

        u.setEmail(usuarioDto.getEmail());
        u.setNome(usuarioDto.getNome());
        u.setRanking(u.getRanking());
        u.setQtdServicosSolicitados(u.getQtdServicosSolicitados());
        u.setQtdServicosFinalizados(u.getQtdServicosFinalizados());
        u.setGenero(usuarioDto.getGenero());
        u.setTipoUsuario(usuarioDto.getTipoUsuario());

        return u;
    }
}
