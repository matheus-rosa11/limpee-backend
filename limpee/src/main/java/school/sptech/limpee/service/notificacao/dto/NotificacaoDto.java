package school.sptech.limpee.service.notificacao.dto;

import school.sptech.limpee.service.FormularioServico.dto.FormularioNotificacaoDto;
import school.sptech.limpee.service.usuario.dto.UsuarioNotificacaoDto;

public class NotificacaoDto {
    private long id;
    private UsuarioNotificacaoDto cliente;
    private FormularioNotificacaoDto formulario;
    private double valorOrcamento;
    private boolean isAprovadoPrestador;
    private boolean isAprovadoCliente;
    private boolean isFinalizado;

    public NotificacaoDto() {
    }

    public NotificacaoDto(long id, FormularioNotificacaoDto formulario, double valorOrcamento, boolean isAprovadoPrestador,boolean isAprovadoCliente) {
        this.id = id;
        this.formulario = formulario;
        this.valorOrcamento = valorOrcamento;
        this.isAprovadoPrestador = isAprovadoPrestador;
        this.isAprovadoCliente = isAprovadoCliente;
    }

    public boolean isAprovadoCliente() {
        return isAprovadoCliente;
    }

    public void setAprovadoCliente(boolean aprovadoCliente) {
        isAprovadoCliente = aprovadoCliente;
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

    public boolean isAprovadoPrestador() {
        return isAprovadoPrestador;
    }

    public void setAprovadoPrestador(boolean aprovadoPrestador) {
        isAprovadoPrestador = aprovadoPrestador;
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

    public boolean isFinalizado() {
        return isFinalizado;
    }

    public void setFinalizado(boolean finalizado) {
        isFinalizado = finalizado;
    }
}
