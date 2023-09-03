package br.com.willbigas.desafioitau.dto;

import lombok.*;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class EstatisticaResponseDTO {

	private Integer count;
	private BigDecimal sum;
	private BigDecimal avg;
	private BigDecimal min;
	private BigDecimal max;
}
