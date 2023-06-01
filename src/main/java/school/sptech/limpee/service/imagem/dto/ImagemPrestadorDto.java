package school.sptech.limpee.service.imagem.dto;

public class ImagemPrestadorDto {
    private Long idImagem;
    private String nome;
    private long idPrestador;
    private byte[] foto;

    public Long getIdImagem() {
        return idImagem;
    }

    public void setIdImagem(Long idImagem) {
        this.idImagem = idImagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getIdPrestador() {
        return idPrestador;
    }

    public void setIdPrestador(long idPrestador) {
        this.idPrestador = idPrestador;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}
