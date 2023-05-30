package school.sptech.limpee.service.usuario.dto;

public class UsuarioCsvDto {
    private String nome;
    private String email;
    private String genero;
    private String tipoUsuario;
    private int anosExperiencia;
    private int ranking;

    public UsuarioCsvDto() {
    }

    public UsuarioCsvDto(String nome, String email, String genero, String tipoUsuario,
                         int anosExperiencia, int ranking) {
        this.nome = nome;
        this.email = email;
        this.genero = genero;
        this.tipoUsuario = tipoUsuario;
        this.anosExperiencia = anosExperiencia;
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public int getAnosExperiencia() {
        return anosExperiencia;
    }

    public void setAnosExperiencia(int anosExperiencia) {
        this.anosExperiencia = anosExperiencia;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }
}
