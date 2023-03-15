package school.sptech.limpee;

import java.util.ArrayList;
import java.util.List;

public abstract class Usuario {
    private String nome;
    private String email;
    private String senha;
    private String genero;
    private int ranking;
    private List<Servico> servicos;

    public Usuario() {}

    public Usuario(String nome, String email, String senha, String genero, int ranking) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.genero = genero;
        this.ranking = ranking;
        this.servicos = new ArrayList<>();
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
    public String logar(String email, String senha){
        if(this.email.equals(email) && this.senha.equals(senha)){
            return "Login realizado com sucesso";
        }
        return "Conta ou senha inválida";
    };
    public double calcularMedia(int qtdServico){
        double notaMedia;
        return notaMedia = (ranking/qtdServico);
    };

    public abstract void calcularPontuacao(Cliente c, int pontuacao);
}
