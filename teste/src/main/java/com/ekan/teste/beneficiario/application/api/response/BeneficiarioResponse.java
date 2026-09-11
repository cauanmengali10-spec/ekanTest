package com.ekan.teste.beneficiario.application.api.response;

import com.ekan.teste.beneficiario.domain.Beneficiario;
import com.ekan.teste.documento.application.api.response.DocumentoResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BeneficiarioResponse {
  private final UUID idBeneficiario;
  private final String nome;
  private final List<DocumentoResponse> documento;
  private final String telefone;
  private final LocalDate dataNascimento;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private final LocalDateTime dataInclusao;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private final LocalDateTime dataAtualizacao;

  public BeneficiarioResponse(Beneficiario beneficiario) {
    this.idBeneficiario = beneficiario.getIdBeneficiario();
    this.nome = beneficiario.getNome();
    this.documento =
        beneficiario.getDocumento().stream()
            .map(DocumentoResponse::new)
            .collect(Collectors.toList());
    this.telefone = beneficiario.getTelefone();
    this.dataNascimento = beneficiario.getDataNascimento();
    this.dataInclusao = beneficiario.getDataInclusao();
    this.dataAtualizacao = beneficiario.getDataAtualizacao();
  }
}
