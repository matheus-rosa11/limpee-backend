package school.sptech.limpee.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cliente extends Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int qtdServicosSolicitados;
    public Cliente() {}

    public Cliente(String nome, String email, String senha, String genero, int ranking) {
        super(nome, email, senha, genero, ranking);
        this.qtdServicosSolicitados = 0;
    }

    public int getQtdServicosSolicitados() {
        return qtdServicosSolicitados;
    }

    public void setQtdServicosSolicitados(int qtdServicosSolicitados) {
        this.qtdServicosSolicitados = qtdServicosSolicitados;
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
