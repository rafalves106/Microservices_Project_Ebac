/**
 * @author falvesmac
 */

package com.clientes.clientes_service.controller;

import com.clientes.clientes_service.domain.Cliente;
import com.clientes.clientes_service.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    IClienteRepository clienteRepository;

    @PostMapping
    public Cliente adicionarCliente(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @GetMapping
    public List<Cliente> buscarTodos(){
        return clienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public Cliente buscarPorId(@PathVariable String id){
        return clienteRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void removerCliente(@PathVariable String id){
        clienteRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Cliente atualizar(@PathVariable String id, @RequestBody Cliente clienteAtualizado){
        return clienteRepository.findById(id)
                .map(cliente -> {
                    cliente.setNome(clienteAtualizado.getNome());
                    cliente.setDataRegistro(clienteAtualizado.getDataRegistro());
                    cliente.setRegiaoOrigem(clienteAtualizado.getRegiaoOrigem());
                    cliente.setQuantidadeInsignias(clienteAtualizado.getQuantidadeInsignias());
                    return clienteRepository.save(cliente);
                })
                .orElseGet(() -> {
                    clienteAtualizado.setId(id);
                    return clienteRepository.save(clienteAtualizado);
        });
    }
}