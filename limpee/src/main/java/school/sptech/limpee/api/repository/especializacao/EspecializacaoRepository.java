package school.sptech.limpee.api.repository.especializacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import school.sptech.limpee.domain.especialidade.Especialidade;
import school.sptech.limpee.domain.especialidade.Especializacao;
import school.sptech.limpee.domain.usuario.Usuario;

import java.util.List;
import java.util.Optional;

@Repository
public interface EspecializacaoRepository extends JpaRepository<Especializacao, Long> {
}
