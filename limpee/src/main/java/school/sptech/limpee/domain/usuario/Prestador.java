package school.sptech.limpee.domain.usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;

public class Prestador extends Usuario {
    public Prestador() {
    }

    public Prestador(String nome, String email, String senha, String genero, int ranking, String tipoUsuario, int qtdServicosSolicitados, int qtdServicosFinalizados, int anosExperiencia) {
        super(nome, email, senha, genero, ranking, tipoUsuario, qtdServicosSolicitados, qtdServicosFinalizados, anosExperiencia);
    }

    @Override
    public void calcularPontuacao(Cliente c, int pontuacao) {

    }
}