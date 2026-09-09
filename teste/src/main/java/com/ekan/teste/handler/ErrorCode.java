package com.ekan.teste.handler;

public enum ErrorCode {
  BENEFICIARIO_NAO_ENCONTRADO("beneficiario.nao.encontrado"),
  BENEFICIARIO_JA_EXISTE("beneficiario.ja.existe"),
  BENEFICIARIO_INVALIDO("beneficiario.invalido"),
  BENEFICIARIO_NOME_INVALIDO("beneficiario.nome.invalido"),
  BENEFICIARIO_CPF_INVALIDO("beneficiario.cpf.invalido"),
  BENEFICIARIO_CPF_JA_EXISTE("beneficiario.cpf.ja.existe"),
  BENEFICIARIO_DATA_NASCIMENTO_INVALIDA("beneficiario.data.nascimento.invalida"),
  BENEFICIARIO_TELEFONE_INVALIDO("beneficiario.telefone.invalido"),
  DOCUMENTO_NAO_ENCONTRADO("documento.nao.encontrado"),
  DOCUMENTO_JA_EXISTE("documento.ja.existe"),
  DOCUMENTO_INVALIDO("documento.invalido"),
  DOCUMENTO_TIPO_INVALIDO("documento.tipo.invalido"),
  DOCUMENTO_DATA_EXPIRACAO_INVALIDA("documento.data.expiracao.invalida");

  private final String code;

  ErrorCode(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }
}
