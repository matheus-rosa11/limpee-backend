package school.sptech.limpee.service.usuario.dto;

public class UsuarioDto {
    private String nome;
    private String email;
    private String genero;
    private int ranking;
    private int qtdServicosSolicitados;
    private String tipoUsuario;

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

    public int getQtdServicosSolicitados() {
        return qtdServicosSolicitados;
    }

    public void setQtdServicosSolicitados(int qtdServicosSolicitados) {
        this.qtdServicosSolicitados = qtdServicosSolicitados;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}