package br.conta.pagamento.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.TaskExecutorJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Bean
    JobLauncher jobLauncher(TaskExecutor jobLauncherTaskExecutor, JobRepository jobRepository) {
        final var jobLauncher = new TaskExecutorJobLauncher();
        jobLauncher.setTaskExecutor(jobLauncherTaskExecutor);
        jobLauncher.setJobRepository(jobRepository);
        return jobLauncher;
    }

}
