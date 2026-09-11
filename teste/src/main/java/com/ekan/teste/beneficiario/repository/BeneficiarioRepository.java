package com.ekan.teste.beneficiario.repository;

import com.ekan.teste.beneficiario.domain.Beneficiario;
import com.ekan.teste.documento.domain.Documento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BeneficiarioRepository {
  Beneficiario salva(Beneficiario beneficiario);

  Page<Beneficiario> listaTodosBeneficiarios(Pageable pageable);

  Page<Documento> listaDocumentosDoBeneficiario(UUID idBeneficiario, Pageable pageable);
}
