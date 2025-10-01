package com.clientes.clientes_service.repository;

import com.clientes.clientes_service.domain.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteRepository extends MongoRepository<Cliente,String> {
}
