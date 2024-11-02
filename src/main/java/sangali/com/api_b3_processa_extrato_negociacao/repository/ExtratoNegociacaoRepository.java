package sangali.com.api_b3_processa_extrato_negociacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sangali.com.api_b3_processa_extrato_negociacao.entity.ExtratoNegociacao;

public interface ExtratoNegociacaoRepository extends JpaRepository<ExtratoNegociacao, Long> {
}
