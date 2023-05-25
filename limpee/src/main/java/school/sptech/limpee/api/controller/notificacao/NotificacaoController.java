package school.sptech.limpee.api.controller.notificacao;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.limpee.service.notificacao.NotificacaoService;
import school.sptech.limpee.service.notificacao.dto.NotificacaoClienteDto;
import school.sptech.limpee.service.notificacao.dto.NotificacaoDto;

import java.util.List;

@RestController
@RequestMapping("notificacoes")
public class NotificacaoController {
    @Autowired
    private NotificacaoService notificacaoService;
    @SecurityRequirement(name = "Bearer")
    @GetMapping("/prestador/{idPrestador}")
    public ResponseEntity<List<NotificacaoDto>> buscarNotificacoes(@PathVariable long idPrestador) {

        List<NotificacaoDto> notificacoes = notificacaoService.buscarNotificacoesPrestador(idPrestador);

        return notificacoes.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(notificacoes);
    }
    @SecurityRequirement(name = "Bearer")
    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<NotificacaoClienteDto>> buscarNotificacoesCliente(@PathVariable long idCliente) {

        List<NotificacaoClienteDto> notificacoes = notificacaoService.buscarNotificacoesCliente(idCliente);

        return notificacoes.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(notificacoes);
    }

    @SecurityRequirement(name = "Bearer")
    @PutMapping("/prestador/aprovar/{idNotificacao}")
    public ResponseEntity<NotificacaoDto> aprovarNotificacao(
            @PathVariable long idNotificacao,
            @RequestParam boolean aprovado,
            @RequestParam double valorOrcamento
    ) {
        notificacaoService.aprovarNotificacaoPrestador(idNotificacao, aprovado, valorOrcamento);

        return ResponseEntity.ok().build();
    }
    @SecurityRequirement(name = "Bearer")
    @PutMapping("/cliente/aprovar/{idNotificacao}")
    public ResponseEntity<NotificacaoDto> aprovarNotificacaoCliente(
            @PathVariable long idNotificacao,
            @RequestParam boolean aprovado
    ) {
        notificacaoService.aprovarNotificacaoCliente(idNotificacao, aprovado);

        return ResponseEntity.ok().build();
    }

    @SecurityRequirement(name = "Bearer")
    @PutMapping("/cliente/finalizar/{idNotificacao}")
    public ResponseEntity<NotificacaoDto> finalizarNotificacao(
            @PathVariable long idNotificacao,
            @RequestParam boolean finalizado
    ) {
        notificacaoService.finalizarNotificacao(idNotificacao, finalizado);
        return ResponseEntity.ok().build();
    }
}
