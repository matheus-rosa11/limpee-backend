package school.sptech.limpee.api.repository.documento;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.limpee.domain.documento.Documento;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {
}
