package school.sptech.limpee.service.imagem.dto;

import school.sptech.limpee.domain.imagem.Imagem;

public class ImagemMapper {
    public static ImagemPrestadorDto of(Imagem imagem) {
        ImagemPrestadorDto i = new ImagemPrestadorDto();

        i.setIdImagem(imagem.getId());
        i.setIdPrestador(imagem.getPrestador().getId());
        i.setNome(imagem.getNome());
        i.setFoto(imagem.getFoto());

        return i;
    }
}
