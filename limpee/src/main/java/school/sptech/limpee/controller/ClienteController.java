package school.sptech.limpee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.limpee.model.Cliente;
import school.sptech.limpee.service.ClienteService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("clientes")
public class ClienteController {
    @Autowired
    ClienteService clienteService;
    List<Cliente> clientes = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes(@RequestHeader int quantidadeClientes) {
        if (quantidadeClientes < 0)
            return ResponseEntity.badRequest().build();

        if (quantidadeClientes == 0) {
            List<Cliente> clientes = clienteService.findAll();

            return clientes.isEmpty() ?
                    ResponseEntity.noContent().build() :
                    ResponseEntity.ok().body(clientes);
        }

        List<Cliente> clientes = clienteService.findFirst4ByOrderByRankingDesc();

        return clientes.isEmpty() ?
                ResponseEntity.status(204).build() :
                ResponseEntity.status(200).body(clientes);
    }
    @PostMapping
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente){
        if(cliente != null) {
            clientes.add(cliente);
            return ResponseEntity.status(201).body(cliente);
        }
        return ResponseEntity.status(400).build();
    }

    @GetMapping("/{nome}")
    public ResponseEntity<List<Cliente>> listarClientePorNome(@PathVariable String nome){
        List<Cliente> clientesListados = new ArrayList<>();
        boolean exists = false;
        for (var c : clientes) {
            if(c.getNome().equals(nome)){
                clientesListados.add(c);
                exists = true;
            }
        }
        return exists ? ResponseEntity.status(201).body(clientesListados) :
        ResponseEntity.status(400).build();
    }
}
