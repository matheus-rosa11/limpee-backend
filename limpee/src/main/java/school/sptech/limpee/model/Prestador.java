package school.sptech.limpee.model;

public class Prestador extends Usuario {
    private int qtdServicoFinalizado;
    private int anosExperiencia;

    public Prestador(String nome, String email, String senha, String genero, int ranking, int anosExperiencia) {
        super(nome, email, senha, genero, ranking);
        this.qtdServicoFinalizado = 0;
        this.anosExperiencia = anosExperiencia;
    }

    public int getQtdServicoFinalizado() {
        return qtdServicoFinalizado;
    }

    public void setQtdServico(int qtdServicoFinalizado) {
        this.qtdServicoFinalizado = qtdServicoFinalizado;
    }

    public int getAnosExperiencia() {
        return anosExperiencia;
    }

    public void setAnosExperiencia(int anosExperiencia) {
        this.anosExperiencia = anosExperiencia;
    }

    public void prestarServico(){

    }

    @Override
    public void calcularPontuacao(Cliente c, int pontuacao) {
        switch (pontuacao){
            case 1:
                c.setRanking(getRanking()-3);
                break;
            case 2:
                c.setRanking(getRanking()-2);
                break;
            case 3:
                c.setRanking(getRanking()+2);
                break;
            case 4:
                c.setRanking(getRanking()+3);
                break;
            case 5:
                c.setRanking(getRanking()+4);
                break;
            default:
                c.setRanking(getRanking()-1);
                break;
        }
    }
}
