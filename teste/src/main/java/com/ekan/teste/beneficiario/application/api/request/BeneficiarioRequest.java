package com.ekan.teste.beneficiario.application.api.request;

import com.ekan.teste.documento.application.api.request.DocumentoRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;
import lombok.*;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeneficiarioRequest {
  @NotBlank(message = "Nome é obrigatório") @Size(max = 255) private String nome;

  @NotBlank(message = "Telefone é obrigatório") @Pattern(regexp = "\\+?[0-9()\\-\\s]+", message = "Telefone inválido")
  private String telefone;

  @NotNull(message = "Data de nascimento é obrigatória") @PastOrPresent(message = "Data de nascimento deve ser no passado ou hoje") private LocalDate dataNascimento;

  @NotEmpty(message = "Cadastre ao menos um documento") @Size(max = 10, message = "Máximo de 10 documentos") @Valid private List<DocumentoRequest> documento;
}
