package school.sptech.limpee.service.FormularioServico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.limpee.api.repository.formularioServico.FormularioServicoRepository;
import school.sptech.limpee.api.repository.notificacao.NotificacaoRepository;
import school.sptech.limpee.api.repository.usuario.UsuarioRepository;
import school.sptech.limpee.domain.FormularioServico.FormularioServico;
import school.sptech.limpee.domain.notificacao.Notificacao;
import school.sptech.limpee.domain.usuario.Usuario;
import school.sptech.limpee.service.FormularioServico.dto.FormularioServicoDTO;
import school.sptech.limpee.service.FormularioServico.dto.FormularioServicoMapper;
import school.sptech.limpee.service.usuario.dto.UsuarioCriacaoDto;
import school.sptech.limpee.service.usuario.dto.UsuarioDto;
import school.sptech.limpee.service.usuario.dto.UsuarioMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FormularioServicoService {
    @Autowired
    FormularioServicoRepository formularioServicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private NotificacaoRepository notificacaoRepository;

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

        FormularioServicoDTO form = FormularioServicoMapper.of(formularioServicoRepository.save(formularioServico));

        this.gerarNotificacaoPrestador(formularioServico);

        return form;
    }

    private void gerarNotificacaoPrestador(FormularioServico formulario) {
        notificacaoRepository.save(new Notificacao(formulario));
    }

    public FormularioServicoDTO atualizarValor(long id, double valor) {
        Optional<FormularioServico> formularioServico = formularioServicoRepository.findById(id);

        if (formularioServico.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        formularioServico.get().setValorServico(valor);
        return FormularioServicoMapper.of(formularioServicoRepository.save(formularioServico.get()));
    }

}
