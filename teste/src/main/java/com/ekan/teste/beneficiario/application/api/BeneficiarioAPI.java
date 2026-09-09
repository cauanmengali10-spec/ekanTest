package com.ekan.teste.beneficiario.application.api;

import com.ekan.teste.beneficiario.application.api.request.BeneficiarioRequest;
import com.ekan.teste.beneficiario.application.api.response.BeneficiarioResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/beneficiario")
public interface BeneficiarioAPI {

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  BeneficiarioResponse criaBeneficiario(BeneficiarioRequest beneficiarioRequest);
}
