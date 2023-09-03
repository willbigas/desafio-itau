package br.com.willbigas.desafioitau.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final String UNPROCESSABLE_ENTITY = "Processamento não executado!";
	private static final String ERRO_PROCESSAR_REQUISICAO = "ERRO: Erro ao processar a requisição";

	@ExceptionHandler(ValorMaximoNaoEncontradoException.class)
	public ResponseEntity<StandardError> trataOrchBraScanException(ValorMaximoNaoEncontradoException e, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY.value())
				.body(StandardError.builder()
						.path(request.getRequestURI())
						.status(HttpStatus.UNPROCESSABLE_ENTITY.value())
						.timestamp(Instant.now())
						.error(UNPROCESSABLE_ENTITY)
						.msg(ERRO_PROCESSAR_REQUISICAO)
						.msgDetails(e.getMessage())
						.build());
	}

	@ExceptionHandler(ValorMinimoNaoEncontradoException.class)
	public ResponseEntity<StandardError> trataOrchBraScanException(ValorMinimoNaoEncontradoException e, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY.value())
				.body(StandardError.builder()
						.path(request.getRequestURI())
						.status(HttpStatus.UNPROCESSABLE_ENTITY.value())
						.timestamp(Instant.now())
						.error(UNPROCESSABLE_ENTITY)
						.msg(ERRO_PROCESSAR_REQUISICAO)
						.msgDetails(e.getMessage())
						.build());
	}

}
