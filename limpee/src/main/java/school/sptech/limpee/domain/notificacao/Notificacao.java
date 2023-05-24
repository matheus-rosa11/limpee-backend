package school.sptech.limpee.domain.notificacao;

import jakarta.persistence.*;
import school.sptech.limpee.domain.FormularioServico.FormularioServico;

@Entity
public class Notificacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private FormularioServico formulario;
    private double valorOrcamento;
    private boolean isAprovadoPrestador;
    private boolean isAprovadoCliente;

    public Notificacao() {
    }

    public Notificacao(FormularioServico formulario) {
        this.formulario = formulario;
        this.isAprovadoPrestador = false;
        this.isAprovadoCliente = false;
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

    public FormularioServico getFormulario() {
        return formulario;
    }

    public void setFormulario(FormularioServico formulario) {
        this.formulario = formulario;
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
}
