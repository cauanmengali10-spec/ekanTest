package com.ekan.teste.documento.application.api.response;

import com.ekan.teste.documento.domain.Documento;
import com.ekan.teste.documento.domain.TipoDocumento;
import java.util.UUID;
import lombok.Getter;

@Getter
public class DocumentoResponse {
  private final UUID idDocumento;
  private final TipoDocumento tipoDocumento;
  private final String descricao;

  public DocumentoResponse(Documento documento) {
    this.idDocumento = documento.getIdDocumento();
    this.tipoDocumento = documento.getTipoDocumento();
    this.descricao = documento.getDescricao();
  }
}
