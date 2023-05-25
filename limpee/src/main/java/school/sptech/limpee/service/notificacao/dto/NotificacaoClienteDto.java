package school.sptech.limpee.service.notificacao.dto;

public class NotificacaoClienteDto {
    private long id;
    private long idPrestador;
    private String nomePrestador;
    private double valorOrcamento;
    private boolean aprovadoByCliente;
    private boolean aprovadoByPrestador;
    private boolean finalizado;

    public NotificacaoClienteDto() {
    }

    public NotificacaoClienteDto(String nomePrestador, double valorOrcamento) {
        this.nomePrestador = nomePrestador;
        this.valorOrcamento = valorOrcamento;
    }

    public String getNomePrestador() {
        return nomePrestador;
    }

    public void setNomePrestador(String nomePrestador) {
        this.nomePrestador = nomePrestador;
    }

    public double getValorOrcamento() {
        return valorOrcamento;
    }

    public void setValorOrcamento(double valorOrcamento) {
        this.valorOrcamento = valorOrcamento;
    }

    public long getIdPrestador() {
        return idPrestador;
    }

    public void setIdPrestador(long idPrestador) {
        this.idPrestador = idPrestador;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public boolean isAprovadoByCliente() {
        return aprovadoByCliente;
    }

    public void setAprovadoByCliente(boolean aprovadoByCliente) {
        this.aprovadoByCliente = aprovadoByCliente;
    }

    public boolean isAprovadoByPrestador() {
        return aprovadoByPrestador;
    }

    public void setAprovadoByPrestador(boolean aprovadoByPrestador) {
        this.aprovadoByPrestador = aprovadoByPrestador;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }
}

