package sangali.com.api_b3_processa_extrato_negociacao.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Table(name = "split")
@Entity(name = "Split")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Split {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String produto;

    private LocalDate dataSplit;

    private Integer multiplo;

    private Boolean process;


}
