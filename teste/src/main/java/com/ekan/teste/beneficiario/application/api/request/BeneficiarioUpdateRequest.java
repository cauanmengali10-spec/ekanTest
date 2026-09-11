package com.ekan.teste.beneficiario.application.api.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class BeneficiarioUpdateRequest {

    @NotBlank(message = "Nome é obrigatório") @Size(max = 255)
    private String nome;

    @NotBlank(message = "Telefone é obrigatório")
    @Pattern(regexp = "\\+?[0-9()\\-\\s]+", message = "Telefone inválido")
    private String telefone;

    @NotNull(message = "Data de nascimento é obrigatória")
    @PastOrPresent(message = "Data de nascimento deve ser no passado ou hoje")
    private LocalDate dataNascimento;
}
