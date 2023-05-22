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
    private boolean isAprovado;

    public Notificacao() {
    }

    public Notificacao(FormularioServico formulario) {
        this.formulario = formulario;
        this.isAprovado = false;
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

    public boolean isAprovado() {
        return isAprovado;
    }

    public void setAprovado(boolean aprovado) {
        isAprovado = aprovado;
    }
}
