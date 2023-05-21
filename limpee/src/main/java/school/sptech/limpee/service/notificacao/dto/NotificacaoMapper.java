package school.sptech.limpee.service.notificacao.dto;

import school.sptech.limpee.domain.notificacao.Notificacao;
import school.sptech.limpee.service.FormularioServico.dto.FormularioServicoMapper;
import school.sptech.limpee.service.usuario.dto.UsuarioMapper;

public class NotificacaoMapper {
    public static NotificacaoDto of(Notificacao notificacao) {
        NotificacaoDto n = new NotificacaoDto();

        n.setId(notificacao.getId());
        n.setValorOrcamento(notificacao.getValorOrcamento());
        n.setAprovado(notificacao.isAprovado());
        n.setCliente(UsuarioMapper.mapToNotificacao(notificacao.getFormulario().getCliente()));
        n.setFormulario(FormularioServicoMapper.mapToFormNotificacaoDto(notificacao.getFormulario()));

        return n;
    }
}
