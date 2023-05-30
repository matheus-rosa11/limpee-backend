package school.sptech.limpee.service.txt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.limpee.domain.txt.Txt;
import school.sptech.limpee.service.endereco.EnderecoService;
import school.sptech.limpee.service.endereco.dto.EnderecoMapper;
import school.sptech.limpee.service.endereco.dto.EnderecoTxtDTO;
import school.sptech.limpee.service.usuario.UsuarioService;
import school.sptech.limpee.service.usuario.dto.UsuarioMapper;
import school.sptech.limpee.service.usuario.dto.UsuarioTxtDto;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class TxtService {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private EnderecoService enderecoService;


    public static void gravaRegistro(String registro, String nomeArq) {
        BufferedWriter saida = null;

        // Bloco try-catch para abrir o arquivo
        try {
            saida = new BufferedWriter(new FileWriter(nomeArq, true));
        } catch (IOException erro) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao abrir o arquivo!");

        }

        // Bloco try-catch para gravar e fechar o arquivo
        try {
            saida.append(registro + "\n");
            saida.close();
        } catch (IOException erro) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao gravar o arquivo!");

        }
    }

    public Txt importArquivoTxt(String nomeArq) {
        nomeArq += ".txt";
        BufferedReader entrada = null;
        String registro, tipoRegistro;
        String nome, email, genero, tipoUsuario, CPF, RG;
        int qtdServicosSolicitados, qtdServicosFinalizados, anosExperiencia, ranking;
        String CEP, complemento, logradouro, bairro, cidade, estado;
        int id, numero;
        int contaRegDadosLidos = 0;
        int qtdRegDadosGravados;
        List<UsuarioTxtDto> usuarioTxtList = new ArrayList<>();
        List<EnderecoTxtDTO> enderecoList = new ArrayList<>();

        try {
            entrada = new BufferedReader(new FileReader(nomeArq));
        } catch (IOException erro) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao abrir o arquivo!");

        }
        try {
            registro = entrada.readLine();
            while (registro != null) {
                tipoRegistro = registro.substring(0, 2);
                if (tipoRegistro.equals("00")) {
                } else if (tipoRegistro.equals("01")) {
                    qtdRegDadosGravados = Integer.parseInt(registro.substring(2, 7));
                } else if (tipoRegistro.equals("02")) {
                    tipoUsuario = registro.substring(2, 11).trim();
                    nome = registro.substring(11, 111).trim();
                    email = registro.substring(111, 156).trim();
                    genero = registro.substring(156, 201).trim();
                    qtdServicosSolicitados = Integer.parseInt(registro.substring(201, 205));
                    qtdServicosFinalizados = Integer.parseInt(registro.substring(205, 209));
                    anosExperiencia = Integer.parseInt(registro.substring(209, 211));
                    ranking = Integer.parseInt(registro.substring(211, 212));
                    CPF = registro.substring(212, 223).trim();
                    RG = registro.substring(223, 232).trim();

                    UsuarioTxtDto u = new UsuarioTxtDto(nome, email, genero, tipoUsuario,
                            qtdServicosSolicitados, qtdServicosFinalizados, anosExperiencia,
                            ranking, CPF, RG);
                    contaRegDadosLidos++;

                    usuarioTxtList.add(u);
                } else if (tipoRegistro.equals("04")) {
                    id = Integer.parseInt(registro.substring(2, 6));
                    CEP = registro.substring(6, 14).trim();
                    complemento = registro.substring(14, 44).trim();
                    logradouro = registro.substring(44, 74).trim();
                    bairro = registro.substring(74, 104).trim();
                    numero = Integer.parseInt(registro.substring(104, 109));
                    cidade = registro.substring(109, 149).trim();
                    estado = registro.substring(149, 169).trim();
                    EnderecoTxtDTO e = new EnderecoTxtDTO(id, CEP, complemento, logradouro, bairro, numero, cidade, estado);
                    enderecoList.add(e);

                } else {
                    throw new ResponseStatusException(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Tipo de registro inválido!");
                }
                registro = entrada.readLine();
            }
            entrada.close();
        } catch (IOException erro) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao ler o arquivo!");
        }
        Txt txt = new Txt(usuarioTxtList, enderecoList);
        return txt;
    }

    public String exportArquivoTxt(String nomeArq) {
        int contaRegDadosGravados = 0;
        List<UsuarioTxtDto> usuarios = usuarioService.findAll().stream().map(UsuarioMapper::mapToUsuarioTxt).toList();
        List<EnderecoTxtDTO> enderecos = enderecoService.findAll().stream().map(EnderecoMapper::mapToTxt).toList();
            
        // Monta o registro de header
        String header = "00USUARIO";
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        header += "01";
        nomeArq += ".txt";

        // Grava o registro de header
        gravaRegistro(header, nomeArq);

        // Monta e grava os registros de dados ou registros de corpo
        String corpo;
        for (int i = 0; i < usuarios.size(); i++) {
            UsuarioTxtDto u = usuarios.get(i);
            corpo = "02";
            corpo += String.format("%-9.9s", u.getTipoUsuario());
            corpo += String.format("%-100.100s", u.getNome());
            corpo += String.format("%-45.45s", u.getEmail());
            corpo += String.format("%-45.45s", u.getGenero());
            corpo += String.format("%04d", u.getQtdServicosSolicitados());
            corpo += String.format("%04d", u.getQtdServicosFinalizados());
            corpo += String.format("%02d", u.getAnosExperiencia());
            corpo += String.format("%01d", u.getRanking());
            corpo += String.format("%-11.11s", u.getCPF());
            corpo += String.format("%-9.9s", u.getRG());
            gravaRegistro(corpo, nomeArq);
            contaRegDadosGravados++;
        }
        for (int i = 0; i < enderecos.size(); i++) {
            EnderecoTxtDTO e = enderecos.get(i);
            corpo = "04";
            corpo += String.format("%04d", e.getId());
            corpo += String.format("%-8.8s", e.getCEP());
            corpo += String.format("%-30.30s", e.getComplemento());
            corpo += String.format("%-30.30s", e.getLogradouro());
            corpo += String.format("%-30.30s", e.getBairro());
            corpo += String.format("%05d", e.getNumero());
            corpo += String.format("%-40.40s", e.getCidade());
            corpo += String.format("%-20.20s", e.getEstado());
            gravaRegistro(corpo, nomeArq);
            contaRegDadosGravados++;
        }

        // Monta e grava o registro de trailer
        String trailer = "01";
        trailer += String.format("%05d", contaRegDadosGravados);
        gravaRegistro(trailer, nomeArq);
        return "Arquivo gerado com sucesso!!";
    }
}
