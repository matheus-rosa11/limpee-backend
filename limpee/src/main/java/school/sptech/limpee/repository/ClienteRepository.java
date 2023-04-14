package school.sptech.limpee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.limpee.domain.usuario.Cliente;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findAllByNome(String nome);
    Optional<Cliente> findByEmailAndSenha(String email, String senha);
    Optional<Cliente> findByEmail(String email);
}
