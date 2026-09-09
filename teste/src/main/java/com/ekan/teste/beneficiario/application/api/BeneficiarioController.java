package com.ekan.teste.beneficiario.application.api;

import com.ekan.teste.beneficiario.application.api.request.BeneficiarioRequest;
import com.ekan.teste.beneficiario.application.api.response.BeneficiarioResponse;
import com.ekan.teste.beneficiario.service.BeneficiarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

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
}
