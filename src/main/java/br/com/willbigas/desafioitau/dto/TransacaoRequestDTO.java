package br.com.willbigas.desafioitau.controller.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoRequestDTO {

	private BigDecimal valor;
	private OffsetDateTime dataHora;

}
