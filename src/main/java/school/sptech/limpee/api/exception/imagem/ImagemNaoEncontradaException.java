package school.sptech.limpee.api.exception.imagem;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Imagem não encontrada!")
public class ImagemNaoEncontradaException extends RuntimeException{
}
