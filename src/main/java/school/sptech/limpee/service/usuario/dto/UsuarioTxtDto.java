package school.sptech.limpee.service.usuario.dto;

public class UsuarioTxtDto {
    private String nome;
    private String email;
    private String genero;
    private String tipoUsuario;
    private int qtdServicosSolicitados;
    private int qtdServicosFinalizados;
    private int anosExperiencia;
    private int ranking;
    private String CPF;
    private String RG;

    public UsuarioTxtDto(String nome, String email, String genero, String tipoUsuario,
                         int qtdServicosSolicitados, int qtdServicosFinalizados,
                         int anosExperiencia, int ranking, String CPF, String RG) {
        this.nome = nome;
        this.email = email;
        this.genero = genero;
        this.tipoUsuario = tipoUsuario;
        this.qtdServicosSolicitados = qtdServicosSolicitados;
        this.qtdServicosFinalizados = qtdServicosFinalizados;
        this.anosExperiencia = anosExperiencia;
        this.ranking = ranking;
        this.CPF = CPF;
        this.RG = RG;
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

    public int getQtdServicosSolicitados() {
        return qtdServicosSolicitados;
    }

    public void setQtdServicosSolicitados(int qtdServicosSolicitados) {
        this.qtdServicosSolicitados = qtdServicosSolicitados;
    }

    public int getQtdServicosFinalizados() {
        return qtdServicosFinalizados;
    }

    public void setQtdServicosFinalizados(int qtdServicosFinalizados) {
        this.qtdServicosFinalizados = qtdServicosFinalizados;
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

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getRG() {
        return RG;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }

    @Override
    public String toString() {
        return "UsuarioTxtDto{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", genero='" + genero + '\'' +
                ", tipoUsuario='" + tipoUsuario + '\'' +
                ", qtdServicosSolicitados=" + qtdServicosSolicitados +
                ", qtdServicosFinalizados=" + qtdServicosFinalizados +
                ", anosExperiencia=" + anosExperiencia +
                ", ranking=" + ranking +
                ", CPF='" + CPF + '\'' +
                ", RG='" + RG + '\'' +
                '}';
    }
}
