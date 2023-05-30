package school.sptech.limpee.service.notificacao.dto;

import school.sptech.limpee.service.FormularioServico.dto.FormularioNotificacaoDto;
import school.sptech.limpee.service.usuario.dto.UsuarioNotificacaoDto;

public class NotificacaoDto {
    private long id;
    private UsuarioNotificacaoDto cliente;
    private FormularioNotificacaoDto formulario;
    private double valorOrcamento;
    private boolean aprovadoByPrestador;
    private boolean aprovadoByCliente;
    private boolean finalizado;

    public NotificacaoDto() {
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

    public boolean isAprovadoByPrestador() {
        return aprovadoByPrestador;
    }

    public void setAprovadoByPrestador(boolean aprovadoByPrestador) {
        this.aprovadoByPrestador = aprovadoByPrestador;
    }

    public boolean isAprovadoByCliente() {
        return aprovadoByCliente;
    }

    public void setAprovadoByCliente(boolean aprovadoByCliente) {
        this.aprovadoByCliente = aprovadoByCliente;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }
}
