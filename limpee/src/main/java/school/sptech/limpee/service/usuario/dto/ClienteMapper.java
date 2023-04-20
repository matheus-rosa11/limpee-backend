package school.sptech.limpee.service.usuario.dto;

import school.sptech.limpee.domain.usuario.Cliente;
import school.sptech.limpee.service.usuario.autenticacao.dto.ClienteTokenDto;

public class ClienteMapper {
    public static Cliente of(ClienteCriacaoDto clienteCriacaoDto) {
        Cliente cliente = new Cliente();

        cliente.setEmail(clienteCriacaoDto.getEmail());
        cliente.setNome(clienteCriacaoDto.getNome());
        cliente.setSenha(clienteCriacaoDto.getSenha());
        cliente.setRanking(clienteCriacaoDto.getRanking());
        cliente.setQtdServicosSolicitados(clienteCriacaoDto.getQtdServicosSolicitados());
        cliente.setGenero(clienteCriacaoDto.getGenero());

        return cliente;
    }

    public static ClienteTokenDto of (Cliente cliente, String token) {
        ClienteTokenDto clienteTokenDto = new ClienteTokenDto();

        clienteTokenDto.setUserId(cliente.getId());
        clienteTokenDto.setEmail(cliente.getEmail());
        clienteTokenDto.setNome(cliente.getNome());
        clienteTokenDto.setToken(token);

        return clienteTokenDto;
    }
}
