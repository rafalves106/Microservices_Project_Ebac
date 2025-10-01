/**
 * @author falvesmac
 */

package com.produtos.produtos_service.controller;

import com.produtos.produtos_service.domain.Produto;
import com.produtos.produtos_service.repository.IProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    IProdutoRepository produtoRepository;

    @PostMapping
    public Produto adicionarProduto(@RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }

    @GetMapping
    public List<Produto> buscarTodos(){
        return produtoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Produto buscarPorId(@PathVariable String id){
        return produtoRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void removerProduto(@PathVariable String id){
        produtoRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Produto atualizar(@PathVariable String id, @RequestBody Produto produtoAtualizado){
        return produtoRepository.findById(id)
                .map(produto -> {
                    produto.setNome(produtoAtualizado.getNome());
                    produto.setDescricao(produtoAtualizado.getDescricao());
                    produto.setPreco(produtoAtualizado.getPreco());
                    return produtoRepository.save(produto);
                })
                .orElseGet(() -> {
                    produtoAtualizado.setId(id);
                    return produtoRepository.save(produtoAtualizado);
        });
    }
}