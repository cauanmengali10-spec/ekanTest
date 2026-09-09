package com.ekan.teste.documento.application.api.response;


import com.ekan.teste.documento.domain.Documento;
import lombok.Getter;

import java.util.UUID;

@Getter
public class DocumentoResponse {
    private final UUID idDocumento;
    private final String tipoDocumento;
    private final String descricao;

    public DocumentoResponse(Documento documento) {
        this.idDocumento = documento.getIdDocumento();
        this.tipoDocumento = documento.getTipoDocumento();
        this.descricao = documento.getDescricao();
    }
}
