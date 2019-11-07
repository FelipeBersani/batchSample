package br.com.example.testesspringboot.batch.processor;

import br.com.example.testesspringboot.domain.Anime;
import br.com.example.testesspringboot.dto.AnimeDTO;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class AnimeProcessor implements ItemProcessor<AnimeDTO, Anime> {

  @Autowired
  private ModelMapper mapper;

  @Override
  public Anime process(AnimeDTO item) throws Exception {
    Anime anime = new Anime();
    mapper.map(item, anime);
    anime.setIdTitle(anime.getId().toString().concat("-" + anime.getTitle()));
    log.info("*** PROCESSOR STEP 1");
    return anime;
  }

}
