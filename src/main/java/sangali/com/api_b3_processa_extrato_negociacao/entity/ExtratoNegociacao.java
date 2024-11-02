package sangali.com.api_b3_processa_extrato_negociacao.entity;

import jakarta.persistence.*;
import lombok.*;
import sangali.com.api_b3_processa_extrato_negociacao.model.ExtratoNegociacaoDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
@Table(name = "extrato_negociacao")
@Entity(name = "ExtratoNegociacao")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class ExtratoNegociacao {

    @Id
    private String hash_negociacao ;

    private LocalDate dataNegocio;

    private String tipoMovimentacao;

    private String codProduto;

    private String instituicao;

    private Integer quantidade;

    private BigDecimal precoUnitario;

    private BigDecimal valorOperacao;

    public ExtratoNegociacao(ExtratoNegociacaoDTO ativos) {

        this.hash_negociacao = ativos.getHashNegociacao();
        this.dataNegocio = ativos.getDataOperacao();
        this.tipoMovimentacao = ativos.getTipoMovimentacao();
        this.codProduto = ativos.getProduto();
        this.instituicao = ativos.getInstituicao();
        this.quantidade = ativos.getQuantidade();
        this.precoUnitario = ativos.getPrecoUnitario();
        this.valorOperacao = ativos.getValorOperacao();
    }


}
