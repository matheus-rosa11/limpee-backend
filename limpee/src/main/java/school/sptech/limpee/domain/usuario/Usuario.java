package school.sptech.limpee.domain.usuario;

import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.*;

@MappedSuperclass
public abstract class Usuario {
    @Size(min = 2, max = 100)
    private String nome;
    @Email
    @NotBlank
    @Size(min = 6, max = 100)
    private String email;
    @NotBlank
    @Size(min = 6, max = 100)
    private String senha;
    @NotBlank
    private String genero;
    private int ranking;

    public Usuario() {}

    public Usuario(String nome, String email, String senha, String genero, int ranking) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.genero = genero;
        this.ranking = ranking;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }
    public double calcularMedia(int qtdServico){
        double notaMedia;
        return notaMedia = (ranking/qtdServico);
    };

    public abstract void calcularPontuacao(Cliente c, int pontuacao);
}
