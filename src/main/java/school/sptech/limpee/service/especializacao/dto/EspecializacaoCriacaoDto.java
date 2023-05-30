package school.sptech.limpee.service.especializacao.dto;

public class EspecializacaoCriacaoDto {
    private long idUsuario;
    private long idEspecialidade;

    public EspecializacaoCriacaoDto() {
    }

    public EspecializacaoCriacaoDto(long idUsuario, long idEspecialidade) {
        this.idUsuario = idUsuario;
        this.idEspecialidade = idEspecialidade;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public long getIdEspecialidade() {
        return idEspecialidade;
    }

    public void setIdEspecialidade(long idEspecialidade) {
        this.idEspecialidade = idEspecialidade;
    }
}
