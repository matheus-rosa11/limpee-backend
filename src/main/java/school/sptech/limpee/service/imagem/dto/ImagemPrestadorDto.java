package school.sptech.limpee.service.imagem.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.sptech.limpee.domain.usuario.Usuario;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ImagemPrestadorDto {
    private Long idImagem;
    private String nome;
    private long idPrestador;
    private byte[] foto;
}
