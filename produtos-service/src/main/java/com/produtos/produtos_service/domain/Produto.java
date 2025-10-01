/**
 * @author falvesmac
 */

package com.produtos.produtos_service.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "produtos")
@Getter
@Setter
public class Produto {

  @Id
  private String id;

  private String nome;

  private String descricao;

  private double preco;

}