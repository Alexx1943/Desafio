package Desafio.Santander.desafioSantander.domain;

import Desafio.Santander.desafioSantander.domain.enumeracao.TipoProduto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private Long codigoProduto;

    @Column(nullable = false)
    private String nomeProduto;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private Double precoUnitario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoProduto tipoProduto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false)
    @JsonBackReference
    private Pedido pedido;


}
