package br.conta.pagamento.batch;

import org.springframework.boot.task.ThreadPoolTaskExecutorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;

@Configuration
public class TaskExecutorConfiguration {

    @Bean
    TaskExecutor jobLauncherTaskExecutor() {
        return new ThreadPoolTaskExecutorBuilder()
            .build();
    }

    @Bean
    TaskExecutor batchTaskExecutor() {
        return new ThreadPoolTaskExecutorBuilder()
            .queueCapacity(0)
            .build();
    }
}
