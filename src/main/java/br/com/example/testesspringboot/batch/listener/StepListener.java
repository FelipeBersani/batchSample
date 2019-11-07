package br.com.example.testesspringboot.batch.listener;

import br.com.example.testesspringboot.config.BatchConfig;
import br.com.example.testesspringboot.domain.Anime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Slf4j
@Component
public class StepListener extends StepExecutionListenerSupport {

  @Autowired
  private JobRepository jobRepository;

  @Autowired
  private Job job2;

  @Override
  public void beforeStep(StepExecution stepExecution) {
    stepExecution.getJobExecution();

  }

  @Override
  public ExitStatus afterStep(StepExecution stepExecution) {

      SimpleJobLauncher simpleJobLauncher = new SimpleJobLauncher();
      simpleJobLauncher.setJobRepository(jobRepository);
      simpleJobLauncher.setTaskExecutor(new SyncTaskExecutor());
    try {
      simpleJobLauncher.run(job2, null);
    } catch (JobExecutionAlreadyRunningException e) {
      e.printStackTrace();
    } catch (JobRestartException e) {
      e.printStackTrace();
    } catch (JobInstanceAlreadyCompleteException e) {
      e.printStackTrace();
    } catch (JobParametersInvalidException e) {
      e.printStackTrace();
    }

    return super.afterStep(stepExecution);
  }
}
