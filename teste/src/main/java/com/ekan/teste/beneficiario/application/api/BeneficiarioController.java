package com.ekan.teste.beneficiario.application.api;

import com.ekan.teste.beneficiario.application.api.request.BeneficiarioRequest;
import com.ekan.teste.beneficiario.application.api.response.BeneficiarioResponse;
import com.ekan.teste.beneficiario.application.api.response.PageResponse;
import com.ekan.teste.beneficiario.domain.Beneficiario;
import com.ekan.teste.beneficiario.service.BeneficiarioService;
import com.ekan.teste.documento.application.api.response.DocumentoResponse;
import com.ekan.teste.documento.domain.Documento;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Log4j2
public class BeneficiarioController implements BeneficiarioAPI {

  private final BeneficiarioService beneficiarioService;

  @Override
  public BeneficiarioResponse criaBeneficiario(BeneficiarioRequest beneficiarioRequest) {
    log.info("Criando beneficiário: {}", beneficiarioRequest);
    return beneficiarioService.criaBeneficiario(beneficiarioRequest);
  }

  @Override
  public PageResponse<BeneficiarioResponse> listarTodosBeneficiarios(int page, int size) {
    log.debug("[start] BeneficiarioController - listarTodosBeneficiarios");
    Page<Beneficiario> beneficiarios =
        beneficiarioService.listarTodosBeneficiarios(PageRequest.of(page, size));
    Page<BeneficiarioResponse> response = beneficiarios.map(BeneficiarioResponse::new);
    log.debug("[finish] BeneficiarioController - listarTodosBeneficiarios");
    return PageResponse.from(response);
  }

  @Override
  public PageResponse<DocumentoResponse> listarDocumentoDoBeneficiario(UUID idBeneficiario, int page, int size) {
    log.debug("[start] BeneficiarioController - listarDocumentoDoBeneficiario");
    Page<Documento> documentos = beneficiarioService.listarDocumentosDoBeneficiario(idBeneficiario, PageRequest.of(page, size));
    Page<DocumentoResponse> response = documentos.map(DocumentoResponse::new);
    return PageResponse.from(response);
  }
}
