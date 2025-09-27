package Desafio.Santander.desafioSantander.dto.request;

import Desafio.Santander.desafioSantander.domain.enumeracao.Canal;
import Desafio.Santander.desafioSantander.domain.enumeracao.Status;
import Desafio.Santander.desafioSantander.domain.enumeracao.TipoDocumento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public class PedidoPutRequest {


    @NotNull(message = "O campo 'numeroPedido' é obrigatório")
    private Long numeroPedido;

    @NotNull(message = "O campo 'tipoDocumento' é obrigatório")
    private TipoDocumento tipoDocumento;

    @NotBlank(message = "O campo 'documentoCliente' é obrigatório")
    private String documentoCliente;

    @NotNull(message = "O campo 'canal' é obrigatório")
    private Canal canal;

    @NotNull(message = "O campo 'status' é obrigatório")
    private Status status;

    private List<ItemRequest> listPedidos;

    @NotBlank(message = "O campo 'gerente' é obrigatório")
    private String gerente;

}
