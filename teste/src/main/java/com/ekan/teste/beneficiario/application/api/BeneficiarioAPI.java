package com.ekan.teste.beneficiario.application.api;

import com.ekan.teste.beneficiario.application.api.request.BeneficiarioRequest;
import com.ekan.teste.beneficiario.application.api.request.BeneficiarioUpdateRequest;
import com.ekan.teste.beneficiario.application.api.response.BeneficiarioResponse;
import com.ekan.teste.beneficiario.application.api.response.PageResponse;
import com.ekan.teste.documento.application.api.response.DocumentoResponse;
import jakarta.validation.Valid;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/beneficiario")
public interface BeneficiarioAPI {

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  BeneficiarioResponse criaBeneficiario(
      @Valid @RequestBody BeneficiarioRequest beneficiarioRequest);

  @GetMapping("/beneficiarios")
  PageResponse<BeneficiarioResponse> listarTodosBeneficiarios(
      @RequestParam(name = "page", defaultValue = "0") int page,
      @RequestParam(name = "size", defaultValue = "10") int size);

  @GetMapping("/documentos/{idBeneficiario}")
  PageResponse<DocumentoResponse> listarDocumentoDoBeneficiario(
      @PathVariable UUID idBeneficiario,
      @RequestParam(name = "page", defaultValue = "0") int page,
      @RequestParam(name = "size", defaultValue = "10") int size);

  @PutMapping("/{idBeneficiario}")
  BeneficiarioResponse atualizaBeneficiario(
      @PathVariable UUID idBeneficiario,
      @Valid @RequestBody BeneficiarioUpdateRequest updateRequest);

  @DeleteMapping("/{idBeneficiario}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  void deletaBeneficiario(@PathVariable UUID idBeneficiario);
}
