package school.sptech.limpee.service.especialidade.dto;

public class EspecialidadeDto {
    private long id;
    private String descricao;

    public EspecialidadeDto() {
    }

    public EspecialidadeDto(String descricao) {
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
