package school.sptech.limpee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.limpee.domain.usuario.Prestador;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrestadorRepository extends JpaRepository<Prestador, Long> {
    List<Prestador> findAllByNome(String nome);

    Optional<Prestador> findByEmailAndSenha(String email, String senha);
}
