package school.sptech.limpee.model;

public class Cliente extends Usuario {
    private int qtdServicoSolicitado;

    public Cliente(String nome, String email, String senha, String genero, int ranking) {
        super(nome, email, senha, genero, ranking);
        this.qtdServicoSolicitado = 0;
    }

    public int getQtdServicoSolicitado() {
        return qtdServicoSolicitado;
    }

    public void setQtdServicoSolicitado(int qtdServicoSolicitado) {
        this.qtdServicoSolicitado = qtdServicoSolicitado;
    }
    public void solicitarServico(){
    }

    @Override
    public void calcularPontuacao(Cliente c,int pontuacao) {
        switch (pontuacao){
            case 1:
                c.setRanking(getRanking()-2);
                break;
            case 2:
                c.setRanking(getRanking()-1);
                break;
            case 3:
                c.setRanking(getRanking()+1);
                break;
            case 4:
                c.setRanking(getRanking()+2);
                break;
            case 5:
                c.setRanking(getRanking()+3);
                break;
            default:
                c.setRanking(getRanking()-1);
                break;
        }

    }
}
