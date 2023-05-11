package school.sptech.limpee.api.repository.avaliacao;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.limpee.domain.avaliacao.Avaliacao;
import school.sptech.limpee.domain.endereco.Endereco;

import java.util.List;

public interface AvaliacaoRepository  extends JpaRepository<Avaliacao, Long> {
    List<Avaliacao> findAll();

}
