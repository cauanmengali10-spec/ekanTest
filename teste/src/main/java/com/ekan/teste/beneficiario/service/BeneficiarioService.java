package com.ekan.teste.beneficiario.service;

import com.ekan.teste.beneficiario.application.api.request.BeneficiarioRequest;
import com.ekan.teste.beneficiario.application.api.request.BeneficiarioUpdateRequest;
import com.ekan.teste.beneficiario.application.api.response.BeneficiarioResponse;
import com.ekan.teste.beneficiario.domain.Beneficiario;
import com.ekan.teste.documento.domain.Documento;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BeneficiarioService {
  BeneficiarioResponse criaBeneficiario(BeneficiarioRequest beneficiarioRequest);

  Page<Beneficiario> listarTodosBeneficiarios(Pageable pageable);

  Page<Documento> listarDocumentosDoBeneficiario(UUID idBeneficiario, Pageable pageable);

  BeneficiarioResponse atualizaBeneficiario(
      UUID idBeneficiario, BeneficiarioUpdateRequest updateRequest);

  void deletaBeneficiario(UUID idBeneficiario);
}
