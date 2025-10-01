/**
 * @author falvesmac
 */

package com.clientes.clientes_service.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "clientes")
public class Cliente {

    @Id
    private String id;
    private String nome;
    private String regiaoOrigem;
    private int quantidadeInsignias;
    private LocalDate dataRegistro;
}