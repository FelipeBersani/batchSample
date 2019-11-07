package br.com.example.testesspringboot.batch.writer;

import br.com.example.testesspringboot.domain.Anime;
import br.com.example.testesspringboot.service.AnimeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class AnimeWriter implements ItemWriter<Anime> {

  @Autowired
  private AnimeService animeService;

  @Override
  public void write(List<? extends Anime> items) throws Exception {
    items.forEach(animeService::saveAnime);
    log.info("**** SALVOU STEP 1");
  }
}
