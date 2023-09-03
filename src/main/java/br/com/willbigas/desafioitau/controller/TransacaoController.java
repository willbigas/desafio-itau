package br.com.willbigas.desafioitau.controller;

import br.com.willbigas.desafioitau.dto.EstatisticaResponseDTO;
import br.com.willbigas.desafioitau.dto.TransacaoRequestDTO;
import br.com.willbigas.desafioitau.service.TransacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping("/transacao")
@RequiredArgsConstructor
public class TransacaoController {

	private final TransacaoService transacaoService;

	@GetMapping
	public List<TransacaoRequestDTO> buscarTodos() {
		return transacaoService.buscarTodos();
	}


	@PostMapping
	public ResponseEntity<String> criarTransacao(@RequestBody TransacaoRequestDTO request) {
		ResponseEntity<String> response = validaDadosDaRequisicao(request);
		transacaoService.inserir(request);
		return response;
	}

	@DeleteMapping
	public ResponseEntity<Void> deletarTransacao() {
		transacaoService.deletar();
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/estatistica")
	public ResponseEntity<EstatisticaResponseDTO> buscarEstatistica() {
		return new ResponseEntity<>(transacaoService.calcularEstatistica() , HttpStatus.OK);
	}


	private ResponseEntity<String> validaDadosDaRequisicao(TransacaoRequestDTO request) {
		if (valoresNaoEstaoPreenchidos(request)) {
			return new ResponseEntity<>("Valor ou DataHora não estão preenchidos corretamente", HttpStatus.UNPROCESSABLE_ENTITY);
		}
		if (dataAtualEstaNoFuturo(request)) {
			return new ResponseEntity<>("DataHora está no futuro, por favor preencha uma data valida!", HttpStatus.UNPROCESSABLE_ENTITY);
		}
		if (valorEhNegativo(request)) {
			return new ResponseEntity<>("Valor da transação esta negativa!", HttpStatus.UNPROCESSABLE_ENTITY);
		}


		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	private static boolean valoresNaoEstaoPreenchidos(TransacaoRequestDTO request) {
		return request.getValor() == null || request.getDataHora() == null;
	}

	private static boolean valorEhNegativo(TransacaoRequestDTO request) {
		return request.getValor().compareTo(BigDecimal.ZERO) < 0;
	}

	private boolean dataAtualEstaNoFuturo(TransacaoRequestDTO request) {
		return request.getDataHora().isAfter(OffsetDateTime.now());
	}


}
