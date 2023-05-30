package school.sptech.limpee.domain.avaliacao;

import jakarta.persistence.*;
import school.sptech.limpee.domain.usuario.Usuario;

@Entity
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String comentario;
    public int nota;

    @ManyToOne
    public Usuario usuario;

    public Avaliacao(Long id, String comentario, int nota) {
        this.id = id;
        this.comentario = comentario;
        this.nota = nota;
    }

    public Avaliacao() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
