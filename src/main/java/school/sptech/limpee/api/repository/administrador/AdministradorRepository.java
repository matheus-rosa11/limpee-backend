package school.sptech.limpee.api.repository.administrador;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.limpee.domain.administrador.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
}
