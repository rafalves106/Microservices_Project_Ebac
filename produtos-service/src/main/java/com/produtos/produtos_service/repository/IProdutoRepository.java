package com.produtos.produtos_service.repository;

import com.produtos.produtos_service.domain.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProdutoRepository extends MongoRepository<Produto,String> {
}
