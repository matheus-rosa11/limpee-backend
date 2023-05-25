package school.sptech.limpee.service.notificacao.dto;

public class NotificacaoClienteDto {
    private long id;
    private long idCliente;
    private String nomeCliente;
    private double valorOrcamento;

    public NotificacaoClienteDto() {
    }

    public NotificacaoClienteDto(String nomeCliente, double valorOrcamento) {
        this.nomeCliente = nomeCliente;
        this.valorOrcamento = valorOrcamento;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public double getValorOrcamento() {
        return valorOrcamento;
    }

    public void setValorOrcamento(double valorOrcamento) {
        this.valorOrcamento = valorOrcamento;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

