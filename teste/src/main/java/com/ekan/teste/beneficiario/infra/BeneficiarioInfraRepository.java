package com.ekan.teste.beneficiario.infra;

import com.ekan.teste.beneficiario.domain.Beneficiario;
import com.ekan.teste.beneficiario.repository.BeneficiarioRepository;
import com.ekan.teste.documento.domain.Documento;
import com.ekan.teste.handler.APIException;
import com.ekan.teste.handler.ErrorCode;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
@Log4j2
public class BeneficiarioInfraRepository implements BeneficiarioRepository {
  private final BeneficiarioJPARepository beneficiarioJPARepository;

  @Override
  public Beneficiario salva(Beneficiario beneficiario) {
    log.debug("[start] BeneficiarioInfraRepository - salva");
    Beneficiario beneficiarioSalvo = beneficiarioJPARepository.save(beneficiario);
    log.debug("[finish] BeneficiarioInfraRepository - salva");
    return beneficiarioSalvo;
  }

  @Override
  public Page<Beneficiario> listaTodosBeneficiarios(Pageable pageable) {
    log.debug("[start] BeneficiarioInfraRepository - listaTodosBeneficiarios");
    Page<Beneficiario> beneficiarios = beneficiarioJPARepository.buscarTodosBeneficiarios(pageable);
    log.debug("[finish] BeneficiarioInfraRepository - listaTodosBeneficiarios");
    return beneficiarios;
  }

  @Override
  public Page<Documento> listaDocumentosDoBeneficiario(UUID idBeneficiario, Pageable pageable) {
    log.debug("[start] BeneficiarioInfraRepository - listaDocumentosDoBeneficiario");
    Beneficiario beneficiario =
        beneficiarioJPARepository
            .findById(idBeneficiario)
            .orElseThrow(
                () ->
                    new APIException(HttpStatus.NOT_FOUND, ErrorCode.BENEFICIARIO_NAO_ENCONTRADO));
    log.debug("[finish] BeneficiarioInfraRepository - listaDocumentosDoBeneficiario");
    return beneficiarioJPARepository.buscaDocumentosDoBeneficiario(idBeneficiario, pageable);
  }

  @Override
  public Beneficiario buscaBeneficiarioPeloId(UUID idBeneficiario) {
    log.debug("[start] BeneficiarioInfraRepository - buscaBeneficiarioPeloId");
    Beneficiario beneficiario =
        beneficiarioJPARepository
            .findById(idBeneficiario)
            .orElseThrow(
                () ->
                    new APIException(HttpStatus.NOT_FOUND, ErrorCode.BENEFICIARIO_NAO_ENCONTRADO));
    log.debug("[finish] BeneficiarioInfraRepository - buscaBeneficiarioPeloId");
    return beneficiario;
  }

  @Override
  public void deletaBeneficiario(UUID idBeneficiario) {
    log.debug("[start] BeneficiarioInfraRepository - deletaBeneficiario");
    Beneficiario beneficiario =
        beneficiarioJPARepository
            .findById(idBeneficiario)
            .orElseThrow(
                () ->
                    new APIException(HttpStatus.NOT_FOUND, ErrorCode.BENEFICIARIO_NAO_ENCONTRADO));
    beneficiarioJPARepository.delete(beneficiario);
    log.debug("[finish] BeneficiarioInfraRepository - deletaBeneficiario");
  }
}
