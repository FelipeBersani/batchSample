package br.com.example.testesspringboot.config;

import br.com.example.testesspringboot.batch.listener.JobCompletionNotificationListener;
import br.com.example.testesspringboot.batch.listener.StepListener;
import br.com.example.testesspringboot.batch.processor.AnimeProcessor;
import br.com.example.testesspringboot.batch.processor.AnimeProcessorStep2;
import br.com.example.testesspringboot.batch.reader.AnimeItemReader;
import br.com.example.testesspringboot.batch.reader.AnimeItemReaderStep2;
import br.com.example.testesspringboot.batch.writer.AnimeWriter;
import br.com.example.testesspringboot.batch.writer.AnimeWriter2;
import br.com.example.testesspringboot.domain.Anime;
import br.com.example.testesspringboot.dto.AnimeDTO;
import br.com.example.testesspringboot.service.AnimeService;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
public class BatchConfig extends DefaultBatchConfigurer {

  private PlatformTransactionManager transactionManager2;

  private static final String PATH_CSV = "C:\\Users\\felipe.liguori\\Documents\\poc\\testes-spring-boot\\src\\main\\resources\\animes.csv";

  @Autowired
  private JobBuilderFactory jobBuilderFactory;

  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Autowired
  private PlatformTransactionManager transactionManager;

  @Autowired
  private AnimeService animeService;


  @Override
  public void setDataSource(DataSource dataSource) {
  }

  @Bean
  public ItemReader<AnimeDTO> itemReader() {
    AnimeItemReader animeItemReader = new AnimeItemReader();

    ColumnPositionMappingStrategy<AnimeDTO> strat = csvConfiguration();
    CsvToBean csvToBean = new CsvToBean();
    List<AnimeDTO> animesList = new ArrayList<>();
    try {
      CSVReader csvReader = new CSVReader(new FileReader(PATH_CSV));
      List list = csvToBean.parse(strat, csvReader);

      list.forEach(item -> animesList.add((AnimeDTO) item));
      animesList.remove(0);
      animeItemReader.setIterator(animesList.iterator());
      log.info("*** READER STEP 1");
      return animeItemReader;
    } catch (FileNotFoundException e) {
      System.out.println("Deu ruim na leitura de arquivo");
      return null;
    }
  }

  @Bean
  public ItemReader<Anime> itemReaderStep2() {
    AnimeItemReaderStep2 animeItemReader = new AnimeItemReaderStep2();
    List<Anime> animeList = animeService.findAnimes();
    animeItemReader.setIterator(animeList.iterator());
    log.info("*** READER STEP 2");
    return animeItemReader;
  }

  @Bean
  public ItemProcessor<AnimeDTO, Anime> processor() {
    return new AnimeProcessor();
  }

  @Bean
  public ItemProcessor<Anime, Anime> processorStep2() {
    return new AnimeProcessorStep2();
  }

  @Bean
  public ItemWriter<Anime> writer() {
    return new AnimeWriter();
  }

  @Bean
  public ItemWriter<Anime> writerStep2() {
    return new AnimeWriter2();
  }

  private ColumnPositionMappingStrategy<AnimeDTO> csvConfiguration() {
    String[] columns = new String[] { "id", "title", "desc" };
    ColumnPositionMappingStrategy<AnimeDTO> strat = new ColumnPositionMappingStrategy<AnimeDTO>();
    strat.setType(AnimeDTO.class);
    strat.setColumnMapping(columns);
    return strat;
  }

  @Bean
  public Job job(JobCompletionNotificationListener listener) {
    return jobBuilderFactory.get("processor")//
        .incrementer(new RunIdIncrementer())//
        .start(step1())
        .build();
  }

  @Bean
  public Step step1() {
    return stepBuilderFactory.get("step1")//
        .transactionManager(transactionManager)//
        .listener(new StepListener())
        .<AnimeDTO, Anime>chunk(10)//
        .reader(itemReader())//
        .processor(processor())//
        .writer(writer())//
        .build();
  }

  @Bean
  public Job job2(JobCompletionNotificationListener listener) {
    return jobBuilderFactory.get("processor2")//
        .incrementer(new RunIdIncrementer())//
        .listener(listener)
        .start(step2())
        .build();
  }

  @Bean
  public Step step2() {
    return stepBuilderFactory.get("step2")//
        .transactionManager(transactionManager)//
        .<Anime, Anime>chunk(10)//
        .reader(itemReaderStep2())//
        .processor(processorStep2())//
        .writer(writerStep2())//
        .build();
  }


}
