package br.conta.pagamento.batch.reader;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import br.conta.pagamento.batch.dto.ContaDto;

@Component
@StepScope
public class ProcessamentoContasReader extends FlatFileItemReader<ContaDto> {
	
	@Value("${file.path}")
	private String pathFiles;

	public ProcessamentoContasReader(@Value("#{stepExecution}") StepExecution stepExecution) {
		
		var fileName = stepExecution.getJobExecution().getJobParameters().getString("FILE_NAME");
		
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		tokenizer.setDelimiter(";");
		tokenizer.setNames("data_vencimento", "data_pagamento", "valor", "descricao", "situacao");
		
		BeanWrapperFieldSetMapper<ContaDto> wrapper = new BeanWrapperFieldSetMapper<>();
		wrapper.setTargetType(ContaDto.class);

		DefaultLineMapper<ContaDto> lineMapper = new DefaultLineMapper<>();
		lineMapper.setLineTokenizer(tokenizer);
		lineMapper.setFieldSetMapper(wrapper);
		
		setName("processamentoContasReader");
		setResource(new FileSystemResource(pathFiles.concat(fileName)));
		setLinesToSkip(1);
		setLineMapper(lineMapper);

	}
}
