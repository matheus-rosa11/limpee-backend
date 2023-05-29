package school.sptech.limpee.api.repository.endereco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.limpee.domain.endereco.Endereco;
import school.sptech.limpee.service.endereco.dto.EnderecoDTO;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    List<Endereco> findAll();

}
