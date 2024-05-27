package br.conta.pagamento.batch.processor;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.conta.pagamento.batch.dto.ContaDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
@StepScope
public class ContaPagamentoProcessor implements ItemProcessor<ContaDto, ContaDto> {

    @Value("#{stepExecution}")
    private final StepExecution stepExecution;

	@Override
	public ContaDto process(ContaDto item) throws Exception {
		return item;
	}
}
