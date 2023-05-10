package school.sptech.limpee.api.repository.especializacao;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.limpee.domain.especializacao.Especializacao;

public interface EspecializacaoRepository extends JpaRepository<Especializacao, Long> {
}
