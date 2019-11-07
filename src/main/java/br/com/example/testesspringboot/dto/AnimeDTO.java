package br.com.example.testesspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimeDTO {

  private String id;
  private String title;

  @Value(value = "description")
  private String desc;

}
