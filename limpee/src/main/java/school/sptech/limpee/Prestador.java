package school.sptech.limpee;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class Prestador extends Usuario{
    private int qtdServico;
    private int anosExperiencia;

    public Prestador(String nome, String email, String senha, String genero, int ranking, int qtdServico, int anosExperiencia) {
        super(nome, email, senha, genero, ranking);
        this.qtdServico = qtdServico;
        this.anosExperiencia = anosExperiencia;
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
