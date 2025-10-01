/**
 * @author falvesmac
 */

package com.clientes.clientes_service.controller;

import com.clientes.clientes_service.domain.Cliente;
import com.clientes.clientes_service.exception.ResourceNotFoundException;
import com.clientes.clientes_service.handler.ApiError;
import com.clientes.clientes_service.repository.IClienteRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    IClienteRepository clienteRepository;

    @Operation(description = "Adiciona um novo cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente adicionado com sucesso",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Cliente.class),
                            examples = @ExampleObject(name = "Exemplo de Cliente",
                            value = """
                                    {
                                      "nome": "Ash Ketchum",
                                      "dataRegistro": "2023-10-01",
                                      "regiaoOrigem": "Kanto",
                                      "quantidadeInsignias": 8
                                    }
                                    """)
                    )),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class),
                            examples = @ExampleObject(name = "Erro de Dados Inválidos",
                                    value = """
                    {
                    "status": 400,
                    "error": "Bad Request",
                    "message": "Dados inválidos fornecidos",
                    "timestamp": "2023-10-03T15:30:00"
                    }
                    """)))
    })
    @PostMapping
    public Cliente adicionarCliente(@Valid @RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Operation(description = "Busca todos os clientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clientes encontrados com sucesso",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Cliente.class),
                        examples = @ExampleObject(name = "Exemplo de Lista de Clientes",
                        value = """
                                {
                                "clientes": [
                                {
                                "id": "945309fjgb405",
                                "nome": "Ash Ketchum",
                                "dataRegistro": "2023-10-01",
                                "regiaoOrigem": "Kanto",
                                "quantidadeInsignias": 8
                                },
                                {
                                "id": "548w945h4508",
                                "nome": "Misty",
                                "dataRegistro": "2023-10-02",
                                "regiaoOrigem": "Cerulean City",
                                "quantidadeInsignias": 5
                                }
                                ]
                                }
                                """)
                )),
            @ApiResponse(responseCode = "404", description = "Nenhum cliente encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class),
                            examples = @ExampleObject(name = "Erro de Recurso Não Encontrado",
                                    value = """
                    {
                    "status": 404,
                    "error": "Not Found",
                    "message": "Nenhum cliente encontrado",
                    "timestamp": "2023-10-03T15:30:00"
                    }
                    """)))
    })
    @GetMapping
    public List<Cliente> buscarTodos(){
        return clienteRepository.findAll();
    }

    @Operation(description = "Busca um cliente pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Cliente.class),
            examples = @ExampleObject(name = "Exemplo de Cliente Retornado",
            value = """
                    {
                    "id": "945309fjgb405",
                    "nome": "Ash Ketchum",
                    "dataRegistro": "2023-10-01",
                    "regiaoOrigem": "Kanto",
                    "quantidadeInsignias": 8
                    }
                    """))),
            @ApiResponse(responseCode = "404", description = "Nenhum cliente encontrado com o ID fornecido",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ApiError.class),
            examples = @ExampleObject(name = "Erro de Recurso Não Encontrado",
            value = """
                    {
                    "status": 404,
                    "error": "Not Found",
                    "message": "Cliente com ID 9396nvg3890 não encontrado",
                    "timestamp": "2023-10-03T15:30:00"
                    }
                    """)))
    })
    @GetMapping("/{id}")
    public Cliente buscarPorId(@Parameter(description = "O ID do cliente a ser buscado") String id){
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente com ID " + id + " não encontrado"));
    }

    @Operation(description = "Remove um cliente pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum cliente encontrado com o ID fornecido",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class),
                            examples = @ExampleObject(name = "Erro de Recurso Não Encontrado",
                                    value = """
                    {
                    "status": 404,
                    "error": "Not Found",
                    "message": "Cliente com ID 9396nvg3890 não encontrado",
                    "timestamp": "2023-10-03T15:30:00"
                    }
                    """)))
    })
    @DeleteMapping("/{id}")
    public void removerCliente(@Parameter(description = "O ID do cliente a ser removido") String id){
        if (!clienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cliente com ID " + id + " não encontrado");
        }
        clienteRepository.deleteById(id);
    }

    @Operation(description = "Atualiza um cliente pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Cliente.class),
            examples = @ExampleObject(name = "Exemplo de Cliente Atualizado",
                    value = """
                            {
                            "nome": "Brock",
                            "dataRegistro": "2023-10-03",
                            "regiaoOrigem": "Pewter City",
                            "quantidadeInsignias": 6
                            }
                            """
            ))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class),
                            examples = @ExampleObject(name = "Erro de Dados Inválidos",
                                    value = """
            {
            "status": 400,
                    "error": "Bad Request",
                    "message": "Dados inválidos fornecidos",
                    "timestamp": "2024-06-01T15:30:00"
                    }
                    """))),
            @ApiResponse(responseCode = "404", description = "Nenhum cliente encontrado com o ID fornecido",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class),
                            examples = @ExampleObject(name = "Erro de Recurso Não Encontrado",
                                    value = """
                    {
                    "status": 404,
                    "error": "Not Found",
                    "message": "Cliente com ID 9396nvg3890 não encontrado",
                    "timestamp": "2023-10-03T15:30:00"
                    }
                    """)))
    })
    @PutMapping("/{id}")
    public Cliente atualizar(@Valid @Parameter(description = "O ID do cliente a ser atualizado") String id, @RequestBody Cliente clienteAtualizado){
        return clienteRepository.findById(id)
                .map(cliente -> {
                    cliente.setNome(clienteAtualizado.getNome());
                    cliente.setDataRegistro(clienteAtualizado.getDataRegistro());
                    cliente.setRegiaoOrigem(clienteAtualizado.getRegiaoOrigem());
                    cliente.setQuantidadeInsignias(clienteAtualizado.getQuantidadeInsignias());
                    return clienteRepository.save(cliente);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Cliente com ID " + id + " não encontrado"));
    }
}