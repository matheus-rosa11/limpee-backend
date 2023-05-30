package school.sptech.limpee.domain.FormularioServico;

import jakarta.persistence.*;
import school.sptech.limpee.domain.usuario.Usuario;

@Entity
public class FormularioServico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    private double valorServico;
    @ManyToOne
    private Usuario cliente;
    @ManyToOne
    private Usuario prestador;

    public FormularioServico(Long id, double valorServico, String tipoServico, String localServico, Boolean areaExterna, Boolean armario, Boolean geladeira, Boolean janelas, Boolean lavarRoupa, Boolean passarRoupa, Boolean outros, String outrosAdcional, int qtdComodos, int qtdBanheiro, Usuario cliente, Usuario prestador) {
        this.id = id;

        this.tipoServico = tipoServico;
        this.valorServico = valorServico;
        this.localServico = localServico;
        this.areaExterna = areaExterna;
        this.armario = armario;
        this.geladeira = geladeira;
        this.janelas = janelas;
        this.lavarRoupa = lavarRoupa;
        this.passarRoupa = passarRoupa;
        this.outros = outros;
        this.outrosAdcional = outrosAdcional;
        this.qtdComodos = qtdComodos;
        this.qtdBanheiro = qtdBanheiro;
        this.cliente = cliente;
        this.prestador = prestador;
    }

    public FormularioServico() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public double getValorServico() {
        return valorServico;
    }

    public void setValorServico(double valorServico) {
        this.valorServico = valorServico;
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

    public Boolean hasAreaExterna() {
        return areaExterna;
    }

    public void setAreaExterna(Boolean areaExterna) {
        this.areaExterna = areaExterna;
    }

    public Boolean hasArmario() {
        return armario;
    }

    public void setArmario(Boolean armario) {
        this.armario = armario;
    }

    public Boolean hasGeladeira() {
        return geladeira;
    }

    public void setGeladeira(Boolean geladeira) {
        this.geladeira = geladeira;
    }

    public Boolean hasJanelas() {
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

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public Usuario getPrestador() {
        return prestador;
    }

    public void setPrestador(Usuario prestador) {
        this.prestador = prestador;
    }
}
