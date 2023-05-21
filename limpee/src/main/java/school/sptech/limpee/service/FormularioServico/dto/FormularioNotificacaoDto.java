package school.sptech.limpee.service.FormularioServico.dto;

import java.util.List;

public class FormularioNotificacaoDto {
    private long id;
    private long idCliente;
    private long idPrestador;
    private String tipoServico;
    private String localServico;
    private int qtdComodos;
    private int qtdBanheiros;
    private List<String> servicos;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<String> getServicos() {
        return servicos;
    }

    public void setServicos(List<String> servicos) {
        this.servicos = servicos;
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    public String getLocalServico() {
        return localServico;
    }

    public void setLocalServico(String localServico) {
        this.localServico = localServico;
    }

    public int getQtdComodos() {
        return qtdComodos;
    }

    public void setQtdComodos(int qtdComodos) {
        this.qtdComodos = qtdComodos;
    }

    public int getQtdBanheiros() {
        return qtdBanheiros;
    }

    public void setQtdBanheiros(int qtdBanheiros) {
        this.qtdBanheiros = qtdBanheiros;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public long getIdPrestador() {
        return idPrestador;
    }

    public void setIdPrestador(long idPrestador) {
        this.idPrestador = idPrestador;
    }
}
