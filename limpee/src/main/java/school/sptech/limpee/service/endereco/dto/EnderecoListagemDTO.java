package school.sptech.limpee.service.endereco.dto;

public class EnderecoListagemDTO extends EnderecoDTO{
    private long idUsuario;

    public EnderecoListagemDTO() {
    }

    public EnderecoListagemDTO(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }
}
