/**
 * @author falvesmac
 */

package com.produtos.produtos_service.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "produtos")
@Schema(description = "Detalhes do produto")
public class Produto {

  @Id
  @Schema(description = "Identificador único do produto", example = "945309fjgb405")
  private String id;

  @NotBlank(message = "O nome do produto é obrigatório")
  @Schema(description = "Nome do produto", example = "Pokébola")
  private String nome;

  @NotBlank(message = "A descrição do produto é obrigatória")
  @Schema(description = "Descrição do produto", example = "Usada para capturar Pokémon")
  private String descricao;

  @Min(value = 0, message = "O preço do produto deve ser um valor positivo")
  @Schema(description = "Preço do produto", example = "99.99", minimum = "0")
  private double preco;

}