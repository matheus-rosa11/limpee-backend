package school.sptech.limpee.api.repository.imagem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.limpee.domain.imagem.Imagem;

import java.util.Optional;

public interface ImagemRepository extends JpaRepository<Imagem, Long> {
    @Modifying
    @Transactional
    @Query("update Imagem i set i.foto = ?2 where i.id = ?1")
    void setFoto(Long id, byte[] foto);

    @Query("select i.foto from Imagem i where i.id = ?1")
    byte[] getFoto(Long id);

    @Query("SELECT i.foto FROM Imagem i WHERE i.prestador.id = :idPrestador")
    Optional<byte[]> getFotoByIdPrestador(Long idPrestador);
}
