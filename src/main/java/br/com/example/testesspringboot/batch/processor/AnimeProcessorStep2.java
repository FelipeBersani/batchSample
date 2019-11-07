package br.com.example.testesspringboot.batch.processor;

import br.com.example.testesspringboot.domain.Anime;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class AnimeProcessorStep2 implements ItemProcessor<Anime, Anime> {

  @Autowired
  public ModelMapper modelMapper;

  @Override
  public Anime process(Anime item) throws Exception {
    item.setDescription(item.getTitle().toUpperCase());
    log.info("***ProcessorSTEP2");
    return item;
  }
}
