package com.ekan.teste.beneficiario.infra;

import com.ekan.teste.beneficiario.domain.Beneficiario;
import java.util.UUID;

import com.ekan.teste.documento.domain.Documento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BeneficiarioJPARepository
    extends JpaRepository<Beneficiario, UUID>, JpaSpecificationExecutor<Beneficiario> {

  @Query(
      value = "SELECT b.idBeneficiario FROM Beneficiario b ORDER BY b.nome ASC",
      countQuery = "SELECT count(b) FROM Beneficiario b")
  Page<UUID> buscarIds(Pageable pageable);


  @EntityGraph(attributePaths = "documento")
  @Query(
      value = "SELECT b FROM Beneficiario b ORDER BY b.nome ASC",
      countQuery = "SELECT count(distinct b) FROM Beneficiario b")
  Page<Beneficiario> buscarTodosBeneficiarios(Pageable pageable);


  @Query(
          value = "SELECT d FROM Documento d "
                  + "WHERE d.beneficiario.idBeneficiario = :id "
                  + "ORDER BY d.dataInclusao ASC",
          countQuery = "SELECT count(d) FROM Documento d "
                  + "WHERE d.beneficiario.idBeneficiario = :id")
  Page<Documento> buscaDocumentosDoBeneficiario(@Param("id") UUID id, Pageable pageable);
}
