package br.com.example.testesspringboot.config;

import br.com.example.testesspringboot.domain.Anime;
import br.com.example.testesspringboot.dto.AnimeDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

  @Bean
  public ModelMapper getModelMapper() {
    ModelMapper mapper = new ModelMapper();
    mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    mapper.addMappings(configureCustomMap());
    return mapper;
  }

  private PropertyMap<AnimeDTO, Anime> configureCustomMap(){
    return new PropertyMap<AnimeDTO, Anime>() {
      @Override
      protected void configure() {
        using(stream -> concatenaTudo(((AnimeDTO) stream.getSource()).getId(),
            ((AnimeDTO) stream.getSource()).getTitle())).map(source, destination.getIdTitle());
        map().setDescription(source.getDesc());
      }
    };
  }

  private String concatenaTudo(String id, String nome){
    return id.concat(" - ").concat(nome);
  }

}
