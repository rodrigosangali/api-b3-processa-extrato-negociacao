package sangali.com.api_b3_processa_extrato_negociacao.repository;


import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import sangali.com.api_b3_processa_extrato_negociacao.entity.Split;

import java.util.Map;

public interface SplitRepository extends JpaRepository<Split, Long> {


    @Query(value ="SELECT t.dataSplit as dataSplit, t.multiplo as multiplo  FROM Split t where t.produto LIKE :produto%")
    Map<String, Object> obtemDataMultiploSplit(String  produto);

    @Transactional
    @Modifying
    @Query("UPDATE Split s SET s.process = :process where s.id = :id")
    void atualizarProcess(Boolean process, Long id);

}
