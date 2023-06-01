package school.sptech.limpee.service.csv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.limpee.domain.csv.CsvAgenda;
import school.sptech.limpee.domain.csv.FilaObj;
import school.sptech.limpee.domain.usuario.Usuario;
import school.sptech.limpee.service.usuario.UsuarioService;
import school.sptech.limpee.service.usuario.dto.UsuarioCsvDto;
import school.sptech.limpee.service.usuario.dto.UsuarioMapper;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.List;

@Service
public class CsvService {
    private FilaObj<CsvAgenda> filaObj = new FilaObj<>(5);
    @Autowired
    private UsuarioService usuarioService;

    public FilaObj<CsvAgenda> listarAgenda() {
        return filaObj;
    }

    public CsvAgenda agendar() {
        List<Usuario> usuarios = new ArrayList<>();

        CsvAgenda csvAgenda = new CsvAgenda(usuarioService.findAllOrderByRanking().stream().map(UsuarioMapper::mapToUsuarioCsv).toList());
        filaObj.insert(csvAgenda);

        List<UsuarioCsvDto> listUsuarioCsvDto = csvAgenda.getUsuarios();

        return csvAgenda;
    }

    public String gravaCsv(String nomeArquivo) {
        List<UsuarioCsvDto> listUsuarioCsvDto = filaObj.poll().getUsuarios();
        FileWriter arq = null;
        Formatter saida = null;
        Boolean deuRuim = false;

        nomeArquivo += ".csv";

        //Bloco try-catch para abrir o arquivo
        try {
            arq = new FileWriter(nomeArquivo);
            saida = new Formatter(arq);
        } catch (IOException erro) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao abrir o arquivo!");
        }
        //Bloco try-catch para gravar no arquivo
        try {
            saida.format("%S;%S;%S;%S;%S;%S\n", "Nome", "email", "genero", "tipo usuario", "anos experiencia", "ranking");

            for (int i = 0; i < listUsuarioCsvDto.size(); i++) {
                UsuarioCsvDto usuarioCsvDto = listUsuarioCsvDto.get(i);
                saida.format("%s;%s;%s;%s;%d;%d\n", usuarioCsvDto.getNome(), usuarioCsvDto.getEmail(), usuarioCsvDto.getGenero(), usuarioCsvDto.getTipoUsuario(), usuarioCsvDto.getAnosExperiencia(), usuarioCsvDto.getRanking());
            }
            System.out.println("Arquivo gerado com sucesso!!");
        } catch (FormatterClosedException erro) {
            deuRuim = true;
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao abrir o arquivo!");

        } finally {
            saida.close();
            try {
                arq.close();
            } catch (IOException erro) {
                deuRuim = true;
            }
            if (deuRuim) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao abrir o arquivo!");

            }
        }
        return "CSV gerado com sucesso";
    }

}
