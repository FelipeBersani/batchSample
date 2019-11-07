package br.com.example.testesspringboot;

import br.com.example.testesspringboot.domain.Ator;
import br.com.example.testesspringboot.domain.Filme;
import br.com.example.testesspringboot.domain.Planet;
import br.com.example.testesspringboot.repository.AtorRepository;
import br.com.example.testesspringboot.repository.FilmeRepository;
import br.com.example.testesspringboot.repository.PlanetRepository;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;

@SpringBootApplication
@EnableBatchProcessing
public class TestesSpringBootApplication{// implements CommandLineRunner {

	@Autowired
	private AtorRepository atorRepository;

	@Autowired
	private FilmeRepository filmeRepository;

	@Autowired
	private PlanetRepository planetRepository;

	public static void main(String[] args) {
		SpringApplication.run(TestesSpringBootApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//    cadastraAtorFilmePlaneta();
//
//	}

	private void cadastraAtorFilmePlaneta(){
    Ator ator = new Ator();
    ator.setNome("Tiburcio");

    Ator ator2 = new Ator();
    ator2.setNome("Bersani");

    Filme f = new Filme();
    f.setDuracao(200L);

    ator.setFilmes(Arrays.asList(f));
    ator2.setFilmes(Arrays.asList(f));

    Planet planetaAbobrinha = new Planet();
    planetaAbobrinha.setNome("Planeta Abobrinha!");
    planetaAbobrinha.setAtores(Arrays.asList(ator, ator2));

    planetRepository.save(planetaAbobrinha);
  }
}
