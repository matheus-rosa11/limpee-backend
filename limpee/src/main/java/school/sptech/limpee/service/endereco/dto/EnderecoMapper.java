package school.sptech.limpee.service.endereco.dto;

import school.sptech.limpee.domain.endereco.Endereco;

public class EnderecoMapper {
    public static Endereco of(EnderecoDTO enderecoDTO){
        Endereco endereco= new Endereco();

        endereco.setCep(enderecoDTO.getCep());
        endereco.setBairro(enderecoDTO.getBairro());
        endereco.setLogradouro(enderecoDTO.getLogradouro());
        endereco.setComplemento(enderecoDTO.getComplemento());

        return endereco;
    }


}
