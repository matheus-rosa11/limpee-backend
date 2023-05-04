package school.sptech.limpee.api.repository.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import school.sptech.limpee.domain.especialidade.Especialidade;
import school.sptech.limpee.domain.usuario.Usuario;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findAllByNome(String nome);
    Optional<Usuario> findByEmailAndSenha(String email, String senha);
    Optional<Usuario> findByEmail(String email);
    List<Usuario> findByTipoUsuarioIgnoreCase(String tipoUsuario);

    List<Usuario> findAllByNomeIgnoreCase(String nome);
    boolean existsByEmail(String email);
}
