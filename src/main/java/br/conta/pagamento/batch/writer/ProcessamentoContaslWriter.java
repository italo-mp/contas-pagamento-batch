package br.conta.pagamento.batch.writer;

import java.time.format.DateTimeFormatter;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import br.conta.pagamento.batch.dto.ContaDto;

@Component
@StepScope
public class ProcessamentoContaslWriter extends JdbcBatchItemWriter<ContaDto> {

	public ProcessamentoContaslWriter(NamedParameterJdbcTemplate jdbcTemplate,
			@Value("#{stepExecution}") StepExecution stepExecution) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		setJdbcTemplate(jdbcTemplate);
		setSql("""
				   INSERT INTO CONTA (DATA_VENCIMENTO, DATA_PAGAMENTO, VALOR, DESCRICAO, SITUACAO) 
				   VALUES (:dataVencimento , :dataPagamento , :valor , :descricao , :situacao)
				""");
		
		setItemSqlParameterSourceProvider(conta -> new MapSqlParameterSource()
	            .addValue("dataVencimento", conta.getDataVencimento().format(formatter))
	            .addValue("dataPagamento", conta.getDataPagamento().format(formatter))
	            .addValue("descricao",conta.getDescricao())
	            .addValue("situacao", conta.getSituacao()));
	}
}
