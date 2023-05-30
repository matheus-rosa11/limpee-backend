package school.sptech.limpee.service.usuario.dto;

import school.sptech.limpee.domain.especializacao.Especializacao;
import school.sptech.limpee.domain.usuario.Usuario;
import school.sptech.limpee.service.FormularioServico.dto.FormularioServicoDTO;
import school.sptech.limpee.service.FormularioServico.dto.FormularioServicoMapper;
import school.sptech.limpee.service.endereco.dto.EnderecoMapper;
import school.sptech.limpee.service.especializacao.dto.EspecializacaoDto;
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
        usuario.setCpf(usuarioCriacaoDto.getCpf());
        usuario.setRg(usuarioCriacaoDto.getRg());
        usuario.setTelefone(usuarioCriacaoDto.getTelefone());
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

        usuarioDto.setId(usuario.getId());
        usuarioDto.setEmail(usuario.getEmail());
        usuarioDto.setNome(usuario.getNome());
        usuarioDto.setRanking(usuario.getRanking());
        usuarioDto.setQtdServicosSolicitados(usuario.getQtdServicosSolicitados());
        usuarioDto.setQtdServicosFinalizados(usuario.getQtdServicosFinalizados());
        usuarioDto.setGenero(usuario.getGenero());
        usuarioDto.setCpf(usuario.getCpf());
        usuarioDto.setRg(usuario.getRg());
        usuarioDto.setTelefone(usuario.getTelefone());
        usuarioDto.setTipoUsuario(usuario.getTipoUsuario());
        usuarioDto.setEndereco(EnderecoMapper.of(usuario.getEndereco()));
        usuarioDto.setEspecializacoes(usuario.getEspecializacoes().stream().map(EspecializacaoMapper::of).toList());

        List<FormularioServicoDTO> formulariosPrestadorDto = usuario.getFormularioPrestador()
                .stream()
                .map(FormularioServicoMapper::of)
                .toList();

        List<FormularioServicoDTO> formulariosClienteDto = usuario.getFormularioCliente()
                .stream()
                .map(FormularioServicoMapper::of)
                .toList();

        if (usuario.getTipoUsuario().equalsIgnoreCase("prestador"))
            usuarioDto.setFormularios(formulariosPrestadorDto);
        else if (usuario.getTipoUsuario().equalsIgnoreCase("cliente"))
            usuarioDto.setFormularios(formulariosClienteDto);

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
        u.setCpf(usuarioDto.getCpf());
        u.setRg(usuarioDto.getRg());
        u.setTelefone(usuarioDto.getTelefone());
        u.setTipoUsuario(usuarioDto.getTipoUsuario());

        return u;
    }


    public static UsuarioNotificacaoDto mapToNotificacao(Usuario cliente) {
        UsuarioNotificacaoDto u = new UsuarioNotificacaoDto();

        u.setId(cliente.getId());
        u.setNome(cliente.getNome());
        u.setEndereco(EnderecoMapper.mapToNotificacao(cliente.getEndereco()));

        return u;
    }
    public static UsuarioCsvDto mapToUsuarioCsv(Usuario usuario) {
        UsuarioCsvDto u = new UsuarioCsvDto();
        u.setNome(usuario.getNome());
        u.setEmail(usuario.getEmail());
        u.setGenero(usuario.getGenero());
        u.setTipoUsuario(usuario.getTipoUsuario());
        u.setRanking(usuario.getRanking());
        u.setAnosExperiencia(usuario.getAnosExperiencia());

        return u;
    }
}
