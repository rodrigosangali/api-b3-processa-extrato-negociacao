package sangali.com.api_b3_processa_extrato_negociacao.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import sangali.com.api_b3_processa_extrato_negociacao.model.ExtratoNegociacaoDTO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import sangali.com.api_b3_processa_extrato_negociacao.utils.SHA1HasGenerator;

@Service
@Slf4j
public class ReadExcelFileService {
    private static final String EXCEL_DIRECTORY = "D:\\WorkspaceJava\\api-b3-processa-extrato-negociacao\\extrato\\negociacao";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Map<String, ExtratoNegociacaoDTO> lerExcelExtratoNegociacao() throws IOException {

        File[] excelFiles = getExcelFiles();

        Map<String, ExtratoNegociacaoDTO> negociacoes = new HashMap<>();

        for (File excelFile : excelFiles) {
            processExcelFile(excelFile, negociacoes);
        }

        return negociacoes;
    }

    private void processExcelFile(File excelFile, Map<String, ExtratoNegociacaoDTO> negociacoes) {
        try (FileInputStream fileInputStream = new FileInputStream(excelFile);
             Workbook workbook = WorkbookFactory.create(fileInputStream)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.rowIterator();

            // Skip the header row
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                ExtratoNegociacaoDTO transaction = parseRowToTransaction(row);

                String hash_negociacao = generateTransactionHash(transaction);

                transaction.setHashNegociacao(hash_negociacao);

                // Soma os valores se a chave já existir
                mergeTransaction(negociacoes, hash_negociacao, transaction);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error processing Excel file: " + excelFile.getName(), e);
        }

        log.info("Arquivo {} processado com sucesso", excelFile.getName());
    }
    private File[] getExcelFiles() {
        File directory = new File(ReadExcelFileService.EXCEL_DIRECTORY);
        File[] files = directory.listFiles();
        if (files == null || files.length == 0) {
            throw new IllegalArgumentException("No Excel files found in the directory: " + ReadExcelFileService.EXCEL_DIRECTORY);
        }
        return files;
    }
    private ExtratoNegociacaoDTO parseRowToTransaction(Row row) {
        ExtratoNegociacaoDTO transaction = new ExtratoNegociacaoDTO();

        transaction.setDataOperacao(LocalDate.parse(row.getCell(0).getStringCellValue(), DATE_FORMATTER));
        transaction.setTipoMovimentacao(row.getCell(1).getStringCellValue());
        transaction.setProduto(row.getCell(5).getStringCellValue());
        transaction.setInstituicao(row.getCell(4).getStringCellValue());
        transaction.setQuantidade((int) row.getCell(6).getNumericCellValue());
        transaction.setPrecoUnitario(BigDecimal.valueOf(row.getCell(7).getNumericCellValue()));
        transaction.setValorOperacao(BigDecimal.valueOf(row.getCell(8).getNumericCellValue()));

        return transaction;
    }
    private String generateTransactionHash(ExtratoNegociacaoDTO transaction) {
        return SHA1HasGenerator.generatorSHA1Hash(
                transaction.getDataOperacao().toString() +
                        transaction.getProduto() +
                        transaction.getTipoOperacao() +
                        transaction.getTipoMovimentacao() +
                        transaction.getInstituicao() +
                        transaction.getPrecoUnitario()
        );
    }
    private void mergeTransaction(Map<String, ExtratoNegociacaoDTO> negociacoes, String transactionHash, ExtratoNegociacaoDTO newTransaction) {
        negociacoes.merge(transactionHash, newTransaction, (existingTransaction, incomingTransaction) -> {
            existingTransaction.setValorOperacao(existingTransaction.getValorOperacao().add(incomingTransaction.getValorOperacao()));
            existingTransaction.setQuantidade(existingTransaction.getQuantidade() + incomingTransaction.getQuantidade());
            return existingTransaction;
        });
    }

}
