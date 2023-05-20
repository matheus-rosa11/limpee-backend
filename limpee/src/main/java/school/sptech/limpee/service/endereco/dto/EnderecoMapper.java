package school.sptech.limpee.service.endereco.dto;

import school.sptech.limpee.domain.endereco.Endereco;
import school.sptech.limpee.domain.usuario.Usuario;

public class EnderecoMapper {
    public static Endereco of(EnderecoDTO enderecoDTO){
        Endereco endereco = new Endereco();

        endereco.setCep(enderecoDTO.getCep());
        endereco.setBairro(enderecoDTO.getBairro());
        endereco.setLogradouro(enderecoDTO.getLogradouro());
        endereco.setComplemento(enderecoDTO.getComplemento());
        endereco.setCidade(enderecoDTO.getCidade());
        endereco.setEstado(enderecoDTO.getEstado());
        endereco.setNumero(enderecoDTO.getNumero());

        return endereco;
    }

//    public static Endereco of(EnderecoDTO enderecoDTO, Usuario usuario){
//        Endereco endereco = new Endereco();
//
//        endereco.setCep(enderecoDTO.getCep());
//        endereco.setBairro(enderecoDTO.getBairro());
//        endereco.setLogradouro(enderecoDTO.getLogradouro());
//        endereco.setComplemento(enderecoDTO.getComplemento());
//        endereco.setCidade(enderecoDTO.getCidade());
//        endereco.setEstado(enderecoDTO.getEstado());
//        endereco.setNumero(enderecoDTO.getNumero());
//        endereco.setUsuario(usuario);
//
//        return endereco;
//    }

    public static EnderecoDTO of(Endereco endereco){
        EnderecoDTO enderecoDTO = new EnderecoDTO();

        enderecoDTO.setCep(endereco.getCep());
        enderecoDTO.setBairro(endereco.getBairro());
        enderecoDTO.setLogradouro(endereco.getLogradouro());
        enderecoDTO.setComplemento(endereco.getComplemento());
        enderecoDTO.setCidade(endereco.getCidade());
        enderecoDTO.setEstado(endereco.getEstado());
        enderecoDTO.setNumero(endereco.getNumero());

        return enderecoDTO;
    }

    public static EnderecoListagemDTO mapToListagem(Endereco endereco) {
        EnderecoListagemDTO enderecoListagemDTO = new EnderecoListagemDTO();

        enderecoListagemDTO.setCep(endereco.getCep());
        enderecoListagemDTO.setBairro(endereco.getBairro());
        enderecoListagemDTO.setLogradouro(endereco.getLogradouro());
        enderecoListagemDTO.setComplemento(endereco.getComplemento());
        enderecoListagemDTO.setCidade(endereco.getCidade());
        enderecoListagemDTO.setEstado(endereco.getEstado());
        enderecoListagemDTO.setNumero(endereco.getNumero());
        enderecoListagemDTO.setIdUsuario(endereco.getUsuario().getId());

        return enderecoListagemDTO;
    }
}
