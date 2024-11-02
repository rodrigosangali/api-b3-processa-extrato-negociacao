package sangali.com.api_b3_processa_extrato_negociacao.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ExtratoNegociacaoDTO {

    private String hashNegociacao;

    private String tipoOperacao;

    private LocalDate dataOperacao;

    private String tipoMovimentacao;

    private String produto;

    private String instituicao;

    private Integer quantidade;

    private BigDecimal precoUnitario;

    private BigDecimal valorOperacao;
}
