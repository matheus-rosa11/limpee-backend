package school.sptech.limpee.api.repository.especializacao;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.limpee.domain.especializacao.Especializacao;

import java.util.List;

public interface EspecializacaoRepository extends JpaRepository<Especializacao, Long> {
    List<Especializacao> findAllByUsuario(long id);
}
