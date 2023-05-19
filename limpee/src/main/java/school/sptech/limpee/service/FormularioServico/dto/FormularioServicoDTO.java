package school.sptech.limpee.service.FormularioServico.dto;

import school.sptech.limpee.service.usuario.dto.UsuarioDto;

public class FormularioServicoDTO {
    private String tipoServico;
    private String localServico;
    private Boolean areaExterna;
    private Boolean armario;
    private Boolean geladeira;
    private Boolean janelas;
    private Boolean lavarRoupa;
    private Boolean passarRoupa;
    private Boolean outros;
    private String outrosAdcional;
    private int qtdComodos;
    private int qtdBanheiro;
//    private UsuarioDto usuarioDto;
    private long cliente;
    private long prestador;


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

    public Boolean getAreaExterna() {
        return areaExterna;
    }

    public void setAreaExterna(Boolean areaExterna) {
        this.areaExterna = areaExterna;
    }

    public Boolean getArmario() {
        return armario;
    }

    public void setArmario(Boolean armario) {
        this.armario = armario;
    }

    public Boolean getGeladeira() {
        return geladeira;
    }

    public void setGeladeira(Boolean geladeira) {
        this.geladeira = geladeira;
    }

    public Boolean getJanelas() {
        return janelas;
    }

    public void setJanelas(Boolean janelas) {
        this.janelas = janelas;
    }

    public Boolean getLavarRoupa() {
        return lavarRoupa;
    }

    public void setLavarRoupa(Boolean lavarRoupa) {
        this.lavarRoupa = lavarRoupa;
    }

    public Boolean getPassarRoupa() {
        return passarRoupa;
    }

    public void setPassarRoupa(Boolean passarRoupa) {
        this.passarRoupa = passarRoupa;
    }

    public Boolean getOutros() {
        return outros;
    }

    public void setOutros(Boolean outros) {
        this.outros = outros;
    }

    public String getOutrosAdcional() {
        return outrosAdcional;
    }

    public void setOutrosAdcional(String outrosAdcional) {
        this.outrosAdcional = outrosAdcional;
    }

    public int getQtdComodos() {
        return qtdComodos;
    }

    public void setQtdComodos(int qtdComodos) {
        this.qtdComodos = qtdComodos;
    }

    public int getQtdBanheiro() {
        return qtdBanheiro;
    }

    public void setQtdBanheiro(int qtdBanheiro) {
        this.qtdBanheiro = qtdBanheiro;
    }

    public long getCliente() {
        return cliente;
    }

    public void setCliente(long cliente) {
        this.cliente = cliente;
    }

    public long getPrestador() {
        return prestador;
    }

    public void setPrestador(long prestador) {
        this.prestador = prestador;
    }
}
