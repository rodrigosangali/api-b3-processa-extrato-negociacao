package sangali.com.api_b3_processa_extrato_negociacao.controller;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sangali.com.api_b3_processa_extrato_negociacao.entity.ExtratoNegociacao;
import sangali.com.api_b3_processa_extrato_negociacao.model.ExtratoNegociacaoDTO;
import sangali.com.api_b3_processa_extrato_negociacao.repository.ExtratoNegociacaoRepository;
import sangali.com.api_b3_processa_extrato_negociacao.service.ReadExcelFileService;
import sangali.com.api_b3_processa_extrato_negociacao.service.SplitService;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("extrato")
@RequiredArgsConstructor
public class Extrato {

    private final ReadExcelFileService realExcelFileService;

    private final ExtratoNegociacaoRepository extratoNegociacaoRepository;

    private final SplitService splitService;

    @PostMapping(value = "/negociacao")
    @Transactional
    public void cadastrarExtratoNegociacao() throws IOException {

        Map<String, ExtratoNegociacaoDTO> linhasExcel = realExcelFileService.lerExcelExtratoNegociacao();

        linhasExcel.forEach( (key, linha) -> {
            extratoNegociacaoRepository.save(new ExtratoNegociacao(linha));
        });
    }


    @PostMapping(value= "/split/b3/processing")
    @Transactional
    public void processarSplit(){

        splitService.executarSplitB3();

    }
}
