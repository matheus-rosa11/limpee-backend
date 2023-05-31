package school.sptech.limpee.api.repository.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import school.sptech.limpee.domain.avaliacao.Avaliacao;
import school.sptech.limpee.domain.usuario.Usuario;
import school.sptech.limpee.service.usuario.dto.UsuarioAvaliacaoDTO;

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
    @Query("SELECT u FROM Usuario u")
    List<Usuario> listarUsuarios();

    @Query("select new school.sptech.limpee.service.usuario.dto.UsuarioAvaliacaoDTO(u.nome, AVG(a.nota)) from Usuario u join u.avaliacoes a GROUP BY u order by AVG(a.nota) DESC ")
    List<UsuarioAvaliacaoDTO> getUsuarioOrderByNota();

    List<Usuario> findAllByOrderByRankingDesc();
}
