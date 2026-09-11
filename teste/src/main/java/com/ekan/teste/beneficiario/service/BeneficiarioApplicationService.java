package com.ekan.teste.beneficiario.service;

import com.ekan.teste.beneficiario.application.api.request.BeneficiarioRequest;
import com.ekan.teste.beneficiario.application.api.response.BeneficiarioResponse;
import com.ekan.teste.beneficiario.domain.Beneficiario;
import com.ekan.teste.beneficiario.repository.BeneficiarioRepository;
import com.ekan.teste.documento.domain.Documento;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class BeneficiarioApplicationService implements BeneficiarioService {

  private final BeneficiarioRepository beneficiarioRepository;

  @Override
  @Transactional
  public BeneficiarioResponse criaBeneficiario(BeneficiarioRequest novoBeneficiario) {
    log.debug("[start] BeneficiarioApplicationService - cadastraBeneficiario");
    Beneficiario beneficiario = new Beneficiario(novoBeneficiario);
    beneficiarioRepository.salva(beneficiario);
    log.debug("[finish] BeneficiarioApplicationService - cadastraBeneficiario");
    return new BeneficiarioResponse(beneficiario);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<Beneficiario> listarTodosBeneficiarios(Pageable pageable) {
    log.debug("[start] BeneficiarioApplicationService - listaBeneficiarios");
    Page<Beneficiario> beneficiarios = beneficiarioRepository.listaTodosBeneficiarios(pageable);
    log.debug("[finish] BeneficiarioApplicationService - listaBeneficiarios");
    return beneficiarios;
  }

  @Override
  @Transactional(readOnly = true)
  public Page<Documento> listarDocumentosDoBeneficiario(UUID idBeneficiario, Pageable pageable) {
    log.debug("[start] BeneficiarioApplicationService - listaDocumentosDoBeneficiario");
    Page<Documento> documentos = beneficiarioRepository.listaDocumentosDoBeneficiario(idBeneficiario, pageable);
    log.debug("[finish] BeneficiarioApplicationService - listaDocumentosDoBeneficiario");
    return documentos;
  }
}
