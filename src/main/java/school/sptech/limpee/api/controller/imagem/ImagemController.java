package school.sptech.limpee.api.controller.imagem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import school.sptech.limpee.api.exception.imagem.ImagemNaoEncontradaException;
import school.sptech.limpee.api.repository.imagem.ImagemRepository;
import school.sptech.limpee.domain.documento.message.ResponseMessage;
import school.sptech.limpee.domain.imagem.Imagem;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("imagens")
@CrossOrigin
public class ImagemController {
    @Autowired
    private ImagemRepository imagemRepository;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseMessage> uploadImage(@RequestBody MultipartFile file){
        try {
            String nome = file.getOriginalFilename();
            byte[] conteudo = file.getBytes();

            Imagem imagem = new Imagem();
            imagem.setNome(nome);
            imagem.setFoto(conteudo);

            imagemRepository.save(imagem);

            return ResponseEntity.ok(new ResponseMessage("Imagem enviada com sucesso!"));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("Falha ao enviar a imagem."));
        }
    }

    @GetMapping
    public ResponseEntity<List<Imagem>> get() {
        List<Imagem> imagens = imagemRepository.findAll();

        return imagens.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(imagens);
    }

    /*
    @PostMapping
    public ResponseEntity<Imagem> criar(@RequestBody Imagem novaImagem) {
        imagemRepository.save(novaImagem);
        return ResponseEntity.status(200).body(novaImagem);
    }*/

    // atualiza a foto de uma planta
    // "consumes" indica o tipo de dado que será aceito no corpo da requisição
    // o mime-type indicado no "consumes" é image/*, que indica que qualquer imagem será aceita
    // uma lista dos tipos de mime-type está em https://mimetype.io/all-types/ ou em https://www.sitepoint.com/mime-types-complete-list/
    @CrossOrigin("*")
    @PatchMapping(value = "/foto/{idImagem}", consumes = "image/png")
    public ResponseEntity<Void> patchFoto(@PathVariable Long idImagem, @RequestBody byte[] novaFoto) {
        if (!imagemRepository.existsById(idImagem)) {
            throw new ImagemNaoEncontradaException();
        }

        imagemRepository.setFoto(idImagem, novaFoto);

        return ResponseEntity.status(200).build();
    }

    // recupera a foto de uma planta
    // "produces" indica o tipo de dado que será entregue no corpo da resposta
    // o mime-type indicado no "produces" é image/jpeg (MediaType.IMAGE_JPEG_VALUE), mas, na prática, qualquer imagem funcionará
    // uma lista dos tipos de mime-type está em https://mimetype.io/all-types/ ou em https://www.sitepoint.com/mime-types-complete-list/
    @GetMapping(value = "/foto/{idImagem}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getFoto(@PathVariable Long idImagem) {
        if (!imagemRepository.existsById(idImagem)) {
            throw new ImagemNaoEncontradaException();
        }

        byte[] foto = imagemRepository.getFoto(idImagem);

        // esse header "content-disposition" indica o nome do arquivo em caso de download em navegador
        return ResponseEntity.status(200).header("content-disposition",
                "attachment; filename=\"foto-usuario.jpg\"").body(foto);
    }
}
