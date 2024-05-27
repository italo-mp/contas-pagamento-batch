package br.conta.pagamento.batch.service;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagamentoContaService {

	@Autowired
	Job processamentoContasJob;
	
	@Autowired
	JobLauncher jobLauncher;

	public void realizarProcessamentoArquivo(String fileName) {
		try {
			jobLauncher.run(processamentoContasJob,
					new JobParametersBuilder().addJobParameter("FILE_NAME", fileName, String.class).toJobParameters());
		} catch (Exception e) {
			throw new RuntimeException("Erro ao iniciar job !", e);
		}
	}
}
