package br.com.example.testesspringboot.batch.reader;

import br.com.example.testesspringboot.domain.Anime;
import br.com.example.testesspringboot.dto.AnimeDTO;
import lombok.Setter;
import org.springframework.batch.item.ItemReader;

import java.util.Iterator;

@Setter
public class AnimeItemReaderStep2 implements ItemReader<Anime> {

  private Iterator<Anime> iterator;

  @Override
  public Anime read() {
    return iterator.hasNext() ? iterator.next() : null;
  }
}
