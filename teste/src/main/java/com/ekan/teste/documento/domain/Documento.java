package com.ekan.teste.documento.domain;

import com.ekan.teste.beneficiario.domain.Beneficiario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "documento")
public class Documento {

  @Id @GeneratedValue private UUID idDocumento;

  @NotNull @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_beneficiario")
  private Beneficiario beneficiario;

  @Column(nullable = false)
  private String tipoDocumento;

  @Column(nullable = false)
  private String descricao;

  @Column(nullable = false)
  private LocalDateTime dataInclusao;

  @Column(nullable = false)
  private LocalDateTime dataAtualizacao;

  @PrePersist
  protected void onCreate() {
    this.dataInclusao = LocalDateTime.now();
    this.dataAtualizacao = LocalDateTime.now();
  }

  @PreUpdate
  protected void onUpdate() {
    this.dataAtualizacao = LocalDateTime.now();
  }
}
