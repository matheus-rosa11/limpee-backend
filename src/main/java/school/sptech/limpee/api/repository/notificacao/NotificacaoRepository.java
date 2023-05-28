package school.sptech.limpee.api.repository.notificacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.limpee.domain.notificacao.Notificacao;
import school.sptech.limpee.service.notificacao.dto.NotificacaoDto;

import java.util.List;

public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {
    @Query("SELECT n FROM Notificacao n WHERE n.formulario.prestador.id = :id")
    List<Notificacao> findAllByIdUsuario(long id);

    @Query("Select n from Notificacao n where n.formulario.cliente.id = :id")
    List<Notificacao> findAllByIdCliente(Long id);


}
