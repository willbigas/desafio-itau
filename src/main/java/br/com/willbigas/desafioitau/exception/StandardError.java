package br.com.willbigas.desafioitau.exception;


import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.time.Instant;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class StandardError {

	private Instant timestamp;
	private Integer status;
	private String error;
	private String msg;
	private String msgDetails;
	private String path;
}
