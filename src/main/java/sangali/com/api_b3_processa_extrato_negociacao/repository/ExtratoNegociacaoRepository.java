package sangali.com.api_b3_processa_extrato_negociacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sangali.com.api_b3_processa_extrato_negociacao.entity.ExtratoNegociacao;

import java.time.LocalDate;
import java.util.List;

public interface ExtratoNegociacaoRepository extends JpaRepository<ExtratoNegociacao, Long> {

    List<ExtratoNegociacao> findByCodProdutoAndTipoMovimentacaoAndDataNegocioBefore(String produto, String movimentacao, LocalDate dataNegociacao);
}
