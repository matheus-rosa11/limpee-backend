package school.sptech.limpee.service.notificacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.limpee.api.repository.notificacao.NotificacaoRepository;
import school.sptech.limpee.domain.FormularioServico.FormularioServico;
import school.sptech.limpee.domain.notificacao.Notificacao;
import school.sptech.limpee.service.notificacao.dto.NotificacaoClienteDto;
import school.sptech.limpee.service.notificacao.dto.NotificacaoDto;
import school.sptech.limpee.service.notificacao.dto.NotificacaoMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NotificacaoService {
    @Autowired
    private NotificacaoRepository notificacaoRepository;

    public List<NotificacaoDto> buscarNotificacoesPrestador(long id) {

        List<Notificacao> notificacoes = notificacaoRepository.findAllByIdUsuario(id);

        if (notificacoes.isEmpty())
            return new ArrayList<>();

        notificacoes = notificacoes.stream().filter(notificacao -> !notificacao.isAprovadoByPrestador() && !notificacao.isFinalizado() && !notificacao.isRecusadoByPrestador()).toList();

        return notificacoes.stream().map(NotificacaoMapper::of).toList();
    }

    public List<NotificacaoClienteDto> buscarNotificacoesCliente(long id) {

        List<Notificacao> notificacoes = notificacaoRepository.findAllByIdCliente(id);

        if (notificacoes.isEmpty())
            return new ArrayList<>();

        notificacoes = notificacoes.stream().filter(notificacao -> !notificacao.isFinalizado()).toList();

        return notificacoes.stream().map(NotificacaoMapper::mapToClienteDto).toList();
    }

    public static List<String> getAllTrue(FormularioServico form) {
        List<String> camposTrue = new ArrayList<>();

        if (form.hasArmario())
            camposTrue.add("armario");
        if (form.hasAreaExterna())
            camposTrue.add("areaExterna");
        if (form.hasGeladeira())
            camposTrue.add("geladeira");
        if (form.hasJanelas())
            camposTrue.add("janelas");
        if (form.getLavarRoupa())
            camposTrue.add("lavarRoupa");
        if (form.getPassarRoupa())
            camposTrue.add("passarRoupa");

        return camposTrue;
    }

    public void aprovarNotificacaoPrestador(long idNotificacao, boolean aprovado, double valorOrcamento) {

        if (valorOrcamento <= 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O valor do orçamento deve ser maior que zero.");

        Optional<Notificacao> notificacao = notificacaoRepository.findById(idNotificacao);

        if (notificacao.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrada nenhuma notificação com o ID especificado");

        if (!aprovado) {
            notificacao.get().setRecusadoByPrestador(true);
            notificacao.get().setAprovadoByPrestador(false);
        }
        else {
            notificacao.get().setValorOrcamento(valorOrcamento);
            notificacao.get().setRecusadoByPrestador(false);
            notificacao.get().setAprovadoByPrestador(true);
        }

        notificacaoRepository.save(notificacao.get());
    }
    public void aprovarNotificacaoCliente(long idNotificacao, boolean aprovado) {

        Optional<Notificacao> notificacao = notificacaoRepository.findById(idNotificacao);

        if (notificacao.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrada nenhuma notificação com o ID especificado");

        if (!aprovado)
            notificacaoRepository.delete(notificacao.get());
        else {
            notificacao.get().setAprovadoByCliente(true);
            notificacaoRepository.save(notificacao.get());
        }

    }

    public void finalizarNotificacao(long idNotificacao, boolean finalizado) {

        Optional<Notificacao> notificacao = notificacaoRepository.findById(idNotificacao);

        if (notificacao.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar a notificação especificada.");

        if (!finalizado)
            notificacaoRepository.delete(notificacao.get());

        if (!notificacao.get().isAprovadoByCliente() || !notificacao.get().isAprovadoByPrestador())
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Só é possível finalizar solicitações aprovadas por ambos os usuários relacionados.");

        notificacao.get().setFinalizado(true);
        notificacaoRepository.save(notificacao.get());
    }
}
