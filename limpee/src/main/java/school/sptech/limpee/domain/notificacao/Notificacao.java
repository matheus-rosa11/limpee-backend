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
    private boolean isAprovadoByPrestador;
    private boolean isAprovadoByCliente;
    private boolean isFinalizado;

    public Notificacao() {
    }

    public Notificacao(FormularioServico formulario) {
        this.formulario = formulario;
        this.isAprovadoByPrestador = false;
        this.isAprovadoByCliente = false;
    }

    public boolean isAprovadoByCliente() {
        return isAprovadoByCliente;
    }

    public void setAprovadoByCliente(boolean aprovadoByCliente) {
        isAprovadoByCliente = aprovadoByCliente;
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

    public boolean isAprovadoByPrestador() {
        return isAprovadoByPrestador;
    }

    public void setAprovadoByPrestador(boolean aprovadoByPrestador) {
        isAprovadoByPrestador = aprovadoByPrestador;
    }

    public boolean isFinalizado() {
        return isFinalizado;
    }

    public void setFinalizado(boolean finalizado) {
        isFinalizado = finalizado;
    }
}
