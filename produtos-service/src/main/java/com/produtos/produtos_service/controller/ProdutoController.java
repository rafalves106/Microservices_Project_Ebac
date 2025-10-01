/**
 * @author falvesmac
 */

package com.produtos.produtos_service.controller;

import com.produtos.produtos_service.domain.Produto;
import com.produtos.produtos_service.exception.ResourceNotFoundException;
import com.produtos.produtos_service.handler.ApiError;
import com.produtos.produtos_service.repository.IProdutoRepository;
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
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    IProdutoRepository produtoRepository;

    @Operation(description = "Adiciona um novo produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto adicionado com sucesso",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Produto.class),
            examples = @ExampleObject(name = "Exemplo de Produto",
            value = """
                    {
                        "nome": "Pokebola",
                        "descricao": "Item usado para capturar pokémons",
                        "preco": 50.0
                    }
                    """))),
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
                    """)))
    })
    @PostMapping
    public Produto adicionarProduto(@Valid @RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }


    @Operation(description = "Busca todos os produtos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produtos encontrados com sucesso",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Produto.class),
            examples = @ExampleObject(name = "Exemplo de Lista de Produtos",
            value = """
                    {
                    "produtos": [
                    {
                    "id": "945309fjgb405",
                    "nome": "Pokebola",
                    "descricao": "Item usado para capturar pokémons",
                    "preco": 50.0
                    },
                    {
                    "id": "945309fjgb406",
                    "nome": "Super Potion",
                    "descricao": "Item usado para curar pokémons",
                    "preco": 100.0
                    }
                    ]
                    }
                    """))),
            @ApiResponse(responseCode = "404", description = "Nenhum produto encontrado",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ApiError.class),
            examples = @ExampleObject(name = "Erro de Produto Não Encontrado",
            value = """
                    {
                    "status": 404,
                    "error": "Not Found",
                    "message": "Nenhum produto encontrado",
                    "timestamp": "2024-06-01T15:30:00"
                    }
                    """)))
    })
    @GetMapping
    public List<Produto> buscarTodos(){
        return produtoRepository.findAll();
    }

    @Operation(description = "Busca um produto pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado com sucesso",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Produto.class),
            examples = @ExampleObject(name = "Exemplo de Produto Retornado",
            value = """
                    {
                    "id": "945309fjgb405",
                    "nome": "Pokebola",
                    "descricao": "Item usado para capturar pokémons",
                    "preco": 50.0
                    }
                    """))),
            @ApiResponse(responseCode = "404", description = "Nenhum produto encontrado com o ID fornecido",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ApiError.class),
            examples = @ExampleObject(name = "Erro de Produto Não Encontrado",
            value = """
                    {
                    "status": 404,
                    "error": "Not Found",
                    "message": "Produto com ID 945309fjgb405 não encontrado",
                    "timestamp": "2024-06-01T15:30:00"
                    }
                    """)))
    })
    @GetMapping("/{id}")
    public Produto buscarPorId(@Parameter(description = "O ID do produto a ser buscado") String id){
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto com ID " + id + " não encontrado"));
    }


    @Operation(description = "Remove um produto pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum produto encontrado com o ID fornecido",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ApiError.class),
            examples = @ExampleObject(name = "Erro de Recurso Não Encontrado",
            value = """
                    {
                    "status": 404,
                    "error": "Not Found",
                    "message": "Produto com ID 945309fjgb405 não encontrado",
                    "timestamp": "2024-06-01T15:30:00"
                    }
                    """)))
    })
    @DeleteMapping("/{id}")
    public void removerProduto(@Parameter(description = "O ID do produto a ser removido") String id){
        produtoRepository.deleteById(id);
    }

    @Operation(description = "Atualiza um produto pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Produto.class),
            examples = @ExampleObject(name = "Exemplo de Produto Atualizado",
            value = """
                    {
                    "id": "945309fjgb405",
                    "nome": "Ultra Ball",
                    "descricao": "Item usado para capturar pokémons com maior eficiência",
                    "preco": 120.0
                    }
                    """))),
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
            @ApiResponse(responseCode = "404", description = "Nenhum produto encontrado com o ID fornecido",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ApiError.class),
            examples = @ExampleObject(name = "Erro de Produto Não Encontrado",
            value = """
                    {
                    "status": 404,
                    "error": "Not Found",
                    "message": "Produto com ID 945309fjgb405 não encontrado",
                    "timestamp": "2024-06-01T15:30:00"
                    }
                    """)))
    })
    @PutMapping("/{id}")
    public Produto atualizar(@Valid @Parameter(description = "O ID do produto a ser atualizado") String id, @RequestBody Produto produtoAtualizado){
        return produtoRepository.findById(id)
                .map(produto -> {
                    produto.setNome(produtoAtualizado.getNome());
                    produto.setDescricao(produtoAtualizado.getDescricao());
                    produto.setPreco(produtoAtualizado.getPreco());
                    return produtoRepository.save(produto);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Produto com ID " + id + " não encontrado"));
    }
}