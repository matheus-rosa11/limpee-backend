package school.sptech.limpee.api.repository.formularioServico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.limpee.domain.FormularioServico.FormularioServico;

import java.util.List;

@Repository
public interface FormularioServicoRepository extends JpaRepository<FormularioServico, Long> {
    List<FormularioServico> findAll();

}
