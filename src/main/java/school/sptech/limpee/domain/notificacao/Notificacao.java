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
    private boolean recusadoByPrestador;
    private boolean aprovadoByPrestador;
    private boolean aprovadoByCliente;
    private boolean finalizado;

    public Notificacao() {
    }

    public Notificacao(FormularioServico formulario) {
        this.formulario = formulario;
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

    public void setRecusadoByPrestador(boolean recusadoByPrestador) {
        this.recusadoByPrestador = recusadoByPrestador;
    }

    public boolean isRecusadoByPrestador() {
        return recusadoByPrestador;
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
