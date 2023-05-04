package school.sptech.limpee.service.especializacao.dto;

import school.sptech.limpee.domain.especialidade.Especialidade;
import school.sptech.limpee.domain.usuario.Usuario;

public class EspecializacaoDto {
    private long id;
    private Usuario usuario;
    private Especialidade especialidade;

    public EspecializacaoDto() {
    }

    public EspecializacaoDto(Usuario usuario, Especialidade especialidade) {
        this.usuario = usuario;
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
