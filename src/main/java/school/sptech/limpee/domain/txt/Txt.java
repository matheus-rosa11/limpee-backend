package school.sptech.limpee.domain.txt;

import school.sptech.limpee.service.endereco.dto.EnderecoTxtDTO;
import school.sptech.limpee.service.usuario.dto.UsuarioTxtDto;

import java.util.List;

public class Txt {
    private List<EnderecoTxtDTO> enderecos;
    private  List<UsuarioTxtDto> usuario;

    public Txt(List<UsuarioTxtDto> usuario,List<EnderecoTxtDTO> enderecos) {
        this.enderecos = enderecos;
        this.usuario = usuario;
    }

    public List<EnderecoTxtDTO> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<EnderecoTxtDTO> enderecos) {
        this.enderecos = enderecos;
    }


    public List<UsuarioTxtDto> getUsuario() {
        return usuario;
    }

    public void setUsuario(List<UsuarioTxtDto> usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Txt{" +
                "enderecos=" + enderecos +
                ", usuario=" + usuario +
                '}';
    }
}
