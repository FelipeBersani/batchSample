package br.com.example.testesspringboot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoDominio {

  PRESIDENTE("presidente"),
  FUNCIONARIO("funcionario");

  private String cargo;

}
