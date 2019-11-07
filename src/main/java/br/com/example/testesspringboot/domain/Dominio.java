package br.com.example.testesspringboot.domain;

import br.com.example.testesspringboot.enums.TipoDominio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dominio {

  private String nome;

  private String dominio;

  private Valores valores;

}
