package school.sptech.limpee.domain.especializacao;

import jakarta.persistence.*;
import lombok.Data;
import school.sptech.limpee.domain.especialidade.Especialidade;
import school.sptech.limpee.domain.usuario.Usuario;

@Entity
public class Especializacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Usuario usuario;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Especialidade especialidade;

    public Especializacao() {
    }

    public Especializacao(Usuario usuario, Especialidade especialidade) {
        this.usuario = usuario;
        this.especialidade = especialidade;
    }

    public Especializacao(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }
}
