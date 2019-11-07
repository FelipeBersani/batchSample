package br.com.example.testesspringboot.batch.reader;

import br.com.example.testesspringboot.dto.AnimeDTO;
import lombok.Setter;
import org.springframework.batch.item.ItemReader;

import java.util.Iterator;

@Setter
public class AnimeItemReader implements ItemReader<AnimeDTO> {

  private Iterator<AnimeDTO> iterator;

  @Override
  public AnimeDTO read() {
    return iterator.hasNext() ? iterator.next() : null;
  }
}
