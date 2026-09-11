package com.ekan.teste.beneficiario.service;

import com.ekan.teste.beneficiario.application.api.request.BeneficiarioRequest;
import com.ekan.teste.beneficiario.application.api.response.BeneficiarioResponse;
import com.ekan.teste.beneficiario.domain.Beneficiario;
import com.ekan.teste.documento.domain.Documento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BeneficiarioService {
  BeneficiarioResponse criaBeneficiario(BeneficiarioRequest beneficiarioRequest);

  Page<Beneficiario> listarTodosBeneficiarios(Pageable pageable);

  Page<Documento> listarDocumentosDoBeneficiario(UUID idBeneficiario, Pageable pageable);
}
