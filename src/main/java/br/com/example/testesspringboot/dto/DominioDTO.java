package br.com.example.testesspringboot.dto;

import br.com.example.testesspringboot.enums.TipoDominio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DominioDTO {

  private String nome;

  private String dominio;

  private String valor1;

  private String valor2;

  private String valor3;

}
