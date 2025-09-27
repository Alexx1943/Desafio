package Desafio.Santander.desafioSantander.dto.request;


import Desafio.Santander.desafioSantander.domain.enumeracao.TipoProduto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ItemPostRequest {


    @NotNull(message = "O campo 'codigoProduto' é obrigatório")
    private Long codigoProduto;

    @NotBlank(message = "O campo 'nomeProduto' é obrigatório")
    private String nomeProduto;

    @NotNull(message = "O campo 'quantidade' é obrigatório")
    private Integer quantidade;

    @NotNull(message = "O campo 'precoUnitario' é obrigatório")
    private Double precoUnitario;


    @NotNull(message = "O campo 'tipoProduto' é obrigatório")
    private TipoProduto tipoProduto;
}
