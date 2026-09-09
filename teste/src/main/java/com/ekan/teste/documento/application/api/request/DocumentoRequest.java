package com.ekan.teste.documento.application.api.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentoRequest {

    @NotBlank(message = "Tipo de documento é obrigatório")
    private String tipoDocumento;
    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

}
