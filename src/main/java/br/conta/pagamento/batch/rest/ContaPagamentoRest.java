package br.conta.pagamento.batch.rest;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.conta.pagamento.batch.dto.ContaDto;
import br.conta.pagamento.batch.service.PagamentoContaService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/processamento-contas")
public class ContaPagamentoRest  {
	
	@Autowired
	private PagamentoContaService service;
	
	@PostMapping(value = "/carregrar-csv/{fileName}", produces = MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ContaDto> carregarArquivoCsv(@RequestParam(name = "fifileNamele", required = false) String fileName) {
		service.realizarProcessamentoArquivo(fileName);
		return ResponseEntity
				.accepted().build();
	}

}
