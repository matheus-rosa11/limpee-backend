package school.sptech.limpee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.limpee.domain.usuario.Prestador;

@Repository
public interface PrestadorRepository extends JpaRepository<Prestador, Long> {
}
