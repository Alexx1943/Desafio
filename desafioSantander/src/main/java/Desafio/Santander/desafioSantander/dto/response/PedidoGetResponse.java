package Desafio.Santander.desafioSantander.dto.response;

import Desafio.Santander.desafioSantander.domain.Item;
import Desafio.Santander.desafioSantander.domain.enumeracao.Status;
import Desafio.Santander.desafioSantander.domain.enumeracao.Canal;
import Desafio.Santander.desafioSantander.domain.enumeracao.TipoDocumento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class PedidoGetResponse {


    private Long id;

    private Long numeroPedido;

    private TipoDocumento tipoDocumento;

    private String documentoCliente;

    private Canal canal;

    private Status status;

    private List<ItemGetResponse> listPedidos;

    private Double valorTotal;

    private String gerente;

    private LocalDateTime dataHoraCriacao;

    private LocalDateTime dataHoraAtualizacao;
}
