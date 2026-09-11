package com.ekan.teste.documento.application.api.request;

import com.ekan.teste.documento.domain.TipoDocumento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentoRequest {

  @NotNull(message = "Tipo de documento é obrigatório") private TipoDocumento tipoDocumento;

  @NotBlank(message = "Descrição é obrigatória") private String descricao;
}
