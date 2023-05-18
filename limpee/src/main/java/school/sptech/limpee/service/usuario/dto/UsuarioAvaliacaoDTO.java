package school.sptech.limpee.service.usuario.dto;

public class UsuarioAvaliacaoDTO {
private String nome;
private double media;


    public UsuarioAvaliacaoDTO(String nome, double media) {
        this.nome = nome;
        this.media = media;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }
}
