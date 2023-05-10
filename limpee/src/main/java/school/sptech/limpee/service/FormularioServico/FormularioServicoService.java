package school.sptech.limpee.service.FormularioServico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.limpee.api.repository.formularioServico.FormularioServicoRepository;
import school.sptech.limpee.domain.FormularioServico.FormularioServico;
import school.sptech.limpee.domain.endereco.Endereco;
import school.sptech.limpee.service.FormularioServico.dto.FormularioServicoDTO;
import school.sptech.limpee.service.FormularioServico.dto.FormularioServicoMapper;
import school.sptech.limpee.service.endereco.dto.EnderecoDTO;
import school.sptech.limpee.service.endereco.dto.EnderecoMapper;

import java.util.List;

@Service
public class FormularioServicoService {
    @Autowired
    FormularioServicoRepository formularioServicoRepository;

    public List<FormularioServico> findAll() {

        return formularioServicoRepository.findAll();
    }
    public FormularioServicoDTO save(FormularioServicoDTO formularioServicoDTO) {
        final FormularioServico formularioServico = FormularioServicoMapper.of(formularioServicoDTO);
        formularioServicoRepository.save(formularioServico);
        return formularioServicoDTO;
    }
}
