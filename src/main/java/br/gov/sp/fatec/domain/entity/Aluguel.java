package br.gov.sp.fatec.domain.entity;

import br.gov.sp.fatec.domain.enums.AluguelStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Date;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Aluguel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double valor;
    private Date dataInicio;
    private Date dataFim;

    @Enumerated(value = EnumType.STRING)
    private AluguelStatus status;

    @OneToMany(mappedBy = "aluguel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Carro> carros;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
