package sangali.com.api_b3_processa_extrato_negociacao.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sangali.com.api_b3_processa_extrato_negociacao.entity.ExtratoNegociacao;
import sangali.com.api_b3_processa_extrato_negociacao.entity.Split;
import sangali.com.api_b3_processa_extrato_negociacao.repository.ExtratoNegociacaoRepository;
import sangali.com.api_b3_processa_extrato_negociacao.repository.SplitRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SplitService {


    private final SplitRepository splitRepository;

    private final ExtratoNegociacaoRepository extratoNegociacaoRepository;
    private List<ExtratoNegociacao> negociacaoList;

    String COMPRA = "Compra";

    public void executarSplitB3() {
        // Busca os produtos
        List<Split> listaSplit = splitRepository.findAll();

        // Realizar a consulta na tabela de split para processamento
        listaSplit.forEach(p -> {
            negociacaoList = extratoNegociacaoRepository.findByCodProdutoAndTipoMovimentacaoAndDataNegocioBefore(p.getProduto(), COMPRA, p.getDataSplit());
            negociacaoList.forEach( t -> {
                 Integer quantidadeNova =    t.getQuantidade() * p.getMultiplo();
                 BigDecimal valorUnitarioNovo = t.getPrecoUnitario().divide(BigDecimal.valueOf(p.getMultiplo()));
                 BigDecimal valorOperacaoNovo = valorUnitarioNovo.multiply(BigDecimal.valueOf(quantidadeNova));

            });
        });


    }


}
