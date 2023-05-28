package school.sptech.limpee.service.especialidade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.limpee.api.repository.especialidade.EspecialidadeRepository;
import school.sptech.limpee.domain.especialidade.Especialidade;
import school.sptech.limpee.service.especialidade.dto.EspecialidadeDto;
import school.sptech.limpee.service.especialidade.dto.EspecialidadeMapper;
import java.util.List;

@Service
public class EspecialidadeService {
    @Autowired
    EspecialidadeRepository especialidadeRepository;

    public Especialidade save(Especialidade especialidade) {
        return especialidadeRepository.save(especialidade);
    }

    public List<EspecialidadeDto> listar() {
        List<Especialidade> especialidades = especialidadeRepository.findAll();
        return especialidades.stream().map(EspecialidadeMapper::of).toList();
    }

    public Especialidade cadastrar(EspecialidadeDto especialidadeDto) {
        Especialidade especialidade = EspecialidadeMapper.of(especialidadeDto);
        return especialidadeRepository.save(especialidade);
    }
}
