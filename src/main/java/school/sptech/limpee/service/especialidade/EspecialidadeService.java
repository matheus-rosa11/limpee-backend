package school.sptech.limpee.service.especialidade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.limpee.api.repository.especialidade.EspecialidadeRepository;
import school.sptech.limpee.domain.especialidade.Especialidade;

import java.util.List;

@Service
public class EspecialidadeService {
    @Autowired
    EspecialidadeRepository especialidadeRepository;

    public List<Especialidade> findAll() {
        return especialidadeRepository.findAll();
    }

    public Especialidade save(Especialidade especialidade) {
        return especialidadeRepository.save(especialidade);
    }

    public boolean existsById(long id) {
        return especialidadeRepository.existsById(id);
    }
}
