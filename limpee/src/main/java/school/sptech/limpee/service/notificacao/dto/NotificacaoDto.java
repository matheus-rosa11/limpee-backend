package school.sptech.limpee.service.notificacao.dto;

import school.sptech.limpee.domain.FormularioServico.FormularioServico;
import school.sptech.limpee.service.FormularioServico.dto.FormularioNotificacaoDto;
import school.sptech.limpee.service.FormularioServico.dto.FormularioServicoDTO;
import school.sptech.limpee.service.usuario.dto.UsuarioNotificacaoDto;

public class NotificacaoDto {
    private long id;
    private UsuarioNotificacaoDto cliente;
    private FormularioNotificacaoDto formulario;
    private double valorOrcamento;
    private boolean isAprovado;

    public NotificacaoDto() {
    }

    public NotificacaoDto(long id, FormularioNotificacaoDto formulario, double valorOrcamento, boolean isAprovado) {
        this.id = id;
        this.formulario = formulario;
        this.valorOrcamento = valorOrcamento;
        this.isAprovado = isAprovado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getValorOrcamento() {
        return valorOrcamento;
    }

    public void setValorOrcamento(double valorOrcamento) {
        this.valorOrcamento = valorOrcamento;
    }

    public boolean isAprovado() {
        return isAprovado;
    }

    public void setAprovado(boolean aprovado) {
        isAprovado = aprovado;
    }

    public FormularioNotificacaoDto getFormulario() {
        return formulario;
    }

    public void setFormulario(FormularioNotificacaoDto formulario) {
        this.formulario = formulario;
    }

    public UsuarioNotificacaoDto getCliente() {
        return cliente;
    }

    public void setCliente(UsuarioNotificacaoDto cliente) {
        this.cliente = cliente;
    }
}
