package school.sptech.limpee.domain.especialidade;

import jakarta.persistence.*;
import school.sptech.limpee.domain.especializacao.Especializacao;

import java.util.List;

@Entity
public class Especialidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String descricao;

    @OneToMany(mappedBy = "especialidade")
    private List<Especializacao> especializacoes;

    public Especialidade() {
    }

    public Especialidade(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
