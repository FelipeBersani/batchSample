package br.com.example.testesspringboot.service;

import br.com.example.testesspringboot.domain.Anime;
import br.com.example.testesspringboot.repository.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimeService {

  @Autowired
  private AnimeRepository animeRepository;

  public void saveAnime(Anime anime){
    anime.setId(null);
    animeRepository.save(anime);
  }

  public void updateAnime(Anime anime){
    animeRepository.save(anime);
  }



  public List<Anime> findAnimes(){
    return animeRepository.findAll();
  }

}
