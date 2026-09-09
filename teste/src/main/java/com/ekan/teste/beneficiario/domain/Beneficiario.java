package com.ekan.teste.beneficiario.domain;

import com.ekan.teste.documento.domain.Documento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "beneficiario")
public class Beneficiario {

  @Id @GeneratedValue private UUID idBeneficiario;

  @Column(nullable = false)
  private String nome;

  @NotNull @OneToMany(
      cascade = CascadeType.ALL,
      fetch = FetchType.LAZY,
      mappedBy = "beneficiario",
      orphanRemoval = true)
  private List<Documento> documento;

  @Column(nullable = false)
  private String telefone;

  @Column(nullable = false)
  private LocalDate dataNascimento;

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
