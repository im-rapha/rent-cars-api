package br.gov.sp.fatec.domain.entity;

import br.gov.sp.fatec.domain.enums.CarroStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modelo;
    private String marca;
    private int ano;

    @Enumerated(value = EnumType.STRING)
    private CarroStatus status;

    @ManyToOne
    @JoinColumn(name = "aluguel_id")
    private Aluguel aluguel;
}
