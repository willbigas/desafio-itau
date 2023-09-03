package br.com.willbigas.desafioitau.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TransacaoRequestDTO {

	private Integer id;

	private BigDecimal valor;

	private OffsetDateTime dataHora;

}
