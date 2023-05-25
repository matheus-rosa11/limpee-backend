package school.sptech.limpee.service.notificacao.dto;

import school.sptech.limpee.domain.notificacao.Notificacao;
import school.sptech.limpee.service.FormularioServico.dto.FormularioServicoMapper;
import school.sptech.limpee.service.usuario.dto.UsuarioMapper;

public class NotificacaoMapper {
    public static NotificacaoDto of(Notificacao notificacao) {
        NotificacaoDto n = new NotificacaoDto();

        n.setId(notificacao.getId());
        n.setValorOrcamento(notificacao.getValorOrcamento());
        n.setAprovadoPrestador(notificacao.isAprovadoByPrestador());
        n.setAprovadoCliente(notificacao.isAprovadoByCliente());
        n.setFinalizado(notificacao.isFinalizado());
        n.setCliente(UsuarioMapper.mapToNotificacao(notificacao.getFormulario().getCliente()));
        n.setFormulario(FormularioServicoMapper.mapToFormNotificacaoDto(notificacao.getFormulario()));

        return n;
    }

    public static NotificacaoClienteDto mapToClienteDto(Notificacao notificacao) {
        NotificacaoClienteDto n = new NotificacaoClienteDto();

        n.setId(notificacao.getId());
        n.setIdCliente(notificacao.getFormulario().getCliente().getId());
        n.setNomeCliente(notificacao.getFormulario().getCliente().getNome());
        n.setValorOrcamento(notificacao.getValorOrcamento());

        return n;
    }
}
