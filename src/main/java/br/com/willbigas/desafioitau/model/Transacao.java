package br.com.willbigas.desafioitau.model;


import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Transacao {

	private Integer id;
	private BigDecimal valor;
	private OffsetDateTime dataHora;
}
