package br.conta.pagamento.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import br.conta.pagamento.batch.dto.ContaDto;
import br.conta.pagamento.batch.reader.ProcessamentoContasReader;
import br.conta.pagamento.batch.writer.ProcessamentoContaslWriter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class ProcessamentoContasJob {

	private final JobRepository jobRepository;
	private final PlatformTransactionManager jobTransactionManager;
	private final ProcessamentoContasReader processamentoContasReader;
	private final ProcessamentoContaslWriter processamentoContaslWriter;

	@Bean
	Job processamentoContas() {
		return new JobBuilder("processamentoContasJob", jobRepository)
				.start(realizarProcessamentoDeContas()).build();
	}

   @Bean
    Step realizarProcessamentoDeContas() {
        return new StepBuilder("realizarCalculoFinalPlaca", jobRepository)
            .<ContaDto, ContaDto>chunk(1000, jobTransactionManager)
            .reader(processamentoContasReader)
            .writer(processamentoContaslWriter)
            .faultTolerant()
            .build();
    }

}
