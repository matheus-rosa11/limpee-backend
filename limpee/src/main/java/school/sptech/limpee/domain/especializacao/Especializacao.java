package school.sptech.limpee.domain.especializacao;

import jakarta.persistence.*;
import lombok.Data;
import school.sptech.limpee.domain.especialidade.Especialidade;
import school.sptech.limpee.domain.usuario.Usuario;

@Data
@Entity
public class Especializacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Especialidade especialidade;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
