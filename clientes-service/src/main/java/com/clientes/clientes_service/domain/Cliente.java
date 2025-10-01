/**
 * @author falvesmac
 */

package com.clientes.clientes_service.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "clientes")
@Schema(description = "Detalhes do cliente")
public class Cliente {

    @Id
    @Schema(description = "Identificador único do cliente", example = "945309fjgb405")
    private String id;

    @NotBlank(message = "O nome do cliente é obrigatório")
    @Schema(description = "Nome do cliente", example = "Ash Ketchum")
    private String nome;

    @NotBlank(message = "A região de origem do cliente é obrigatória")
    @Schema(description = "Região de origem do cliente", example = "Kanto")
    private String regiaoOrigem;

    @Min(value = 0, message = "A quantidade de insígnias deve ser um valor positivo")
    @Schema(description = "Quantidade de insígnias do cliente", example = "8", minimum = "0")
    private int quantidadeInsignias;

    @NotNull(message = "A data de registro do cliente é obrigatória")
    @Schema(description = "Data de registro do cliente", example = "2023-10-01")
    private LocalDate dataRegistro;
}