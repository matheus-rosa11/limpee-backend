package school.sptech.limpee.service.FormularioServico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.limpee.api.repository.formularioServico.FormularioServicoRepository;
import school.sptech.limpee.domain.FormularioServico.FormularioServico;
import school.sptech.limpee.domain.usuario.Usuario;
import school.sptech.limpee.service.FormularioServico.dto.FormularioServicoDTO;
import school.sptech.limpee.service.FormularioServico.dto.FormularioServicoMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class FormularioServicoService {
    @Autowired
    FormularioServicoRepository formularioServicoRepository;

    public List<FormularioServicoDTO> findAll() {
        List<FormularioServico> formularioServicos = formularioServicoRepository.findAll();
        List<FormularioServicoDTO> formularioServicosDTO = new ArrayList<>();

        formularioServicos = formularioServicos
                .stream()
                .filter(
                        formularioServico ->
                                formularioServico.getCliente() != null || formularioServico.getPrestador() != null)
                .collect(Collectors.toList());

        for (FormularioServico f : formularioServicos) {
            formularioServicosDTO.add(FormularioServicoMapper.of(f));
        }

        return formularioServicosDTO;
    }
    public FormularioServicoDTO save(FormularioServicoDTO formularioServicoDTO, Usuario cliente, Usuario prestador) {
        final FormularioServico formularioServico = FormularioServicoMapper.of(formularioServicoDTO, cliente, prestador);
        return FormularioServicoMapper.of(formularioServicoRepository.save(formularioServico));
    }
}
