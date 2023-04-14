package school.sptech.limpee.service.usuario.dto;

import school.sptech.limpee.domain.usuario.Cliente;

public class ClienteMapper {
    public static Cliente of(ClienteCriacaoDto clienteCriacaoDto) {
        Cliente cliente = new Cliente();

        cliente.setEmail(clienteCriacaoDto.getEmail());
        cliente.setNome(clienteCriacaoDto.getNome());
        cliente.setSenha(clienteCriacaoDto.getSenha());

        return cliente;
    }
}
