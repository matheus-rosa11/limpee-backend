package school.sptech.limpee.api.repository.documento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.limpee.domain.documento.Documento;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {
    @Query("SELECT d FROM Documento d WHERE d.usuario.id = :idPrestador")
    Documento findByIdPrestador(long idPrestador);
}
