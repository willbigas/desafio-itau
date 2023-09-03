package br.com.willbigas.desafioitau.service;

import br.com.willbigas.desafioitau.dto.EstatisticaResponseDTO;
import br.com.willbigas.desafioitau.dto.TransacaoRequestDTO;
import br.com.willbigas.desafioitau.exception.ValorMaximoNaoEncontradoException;
import br.com.willbigas.desafioitau.exception.ValorMinimoNaoEncontradoException;
import br.com.willbigas.desafioitau.model.Transacao;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.random.RandomGenerator;
import java.util.stream.Collectors;

@Service
public class TransacaoService {

	public static final String MSG_VALOR_MAXIMO_TRANSACOES = "Valor máximo da lista de transações não foi encontrado";
	public static final String MSG_VALOR_MINIMO_TRANSACOES = "Valor mínimo da lista de transações não foi encontrado";

	private List<Transacao> transacoes;

	public TransacaoService() {
		transacoes = new ArrayList<>();
	}

	public void inserir(TransacaoRequestDTO dto) {
		Transacao transacao = Transacao.builder()
				.id(RandomGenerator.getDefault().nextInt())
				.dataHora(dto.getDataHora())
				.valor(dto.getValor())
				.build();
		transacoes.add(transacao);
	}

	public void deletar() {
		transacoes.clear();
	}

	public List<TransacaoRequestDTO> buscarTodos() {
		return transacoes.stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	public EstatisticaResponseDTO calcularEstatistica() {
		BigDecimal valorMaximo = transacoes.stream()
				.filter(Objects::nonNull)
				.max(Comparator.comparing(Transacao::getValor))
				.orElseThrow(() -> new ValorMaximoNaoEncontradoException(MSG_VALOR_MAXIMO_TRANSACOES)).getValor();

		BigDecimal valorMinimo = transacoes.stream()
				.filter(Objects::nonNull)
				.min(Comparator.comparing(Transacao::getValor))
				.orElseThrow(() -> new ValorMinimoNaoEncontradoException(MSG_VALOR_MINIMO_TRANSACOES)).getValor();

		BigDecimal media = BigDecimal.valueOf(transacoes.stream()
				.filter(Objects::nonNull)
				.mapToDouble(transacao -> transacao.getValor().doubleValue())
				.average()
				.orElse(0.0));

		BigDecimal soma = BigDecimal.valueOf(transacoes.stream()
				.filter(Objects::nonNull)
				.mapToDouble(transacao -> transacao.getValor().doubleValue())
				.sum());

		return EstatisticaResponseDTO.builder()
				.count(transacoes.size())
				.max(valorMaximo)
				.min(valorMinimo)
				.sum(soma)
				.avg(media)
				.build();
	}


	private TransacaoRequestDTO mapToDTO(Transacao transacao) {
		return TransacaoRequestDTO.builder()
				.id(transacao.getId())
				.dataHora(transacao.getDataHora())
				.valor(transacao.getValor())
				.build();
	}
}
