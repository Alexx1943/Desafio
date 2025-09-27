package Desafio.Santander.desafioSantander.domain;

import Desafio.Santander.desafioSantander.domain.enumeracao.Canal;
import Desafio.Santander.desafioSantander.domain.enumeracao.Status;
import Desafio.Santander.desafioSantander.domain.enumeracao.TipoDocumento;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pedido {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column()
    private Long numeroPedido;


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;

    @Column(nullable = false)
    private String documentoCliente;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Canal canal;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Item> listPedidos = new ArrayList<>();

    @Column()
    private Double valorTotal;

    @Column(nullable = false)
    private String gerente;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataHoraCriacao;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataHoraAtualizacao;




}
