package Desafio.Santander.desafioSantander.dto.request;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ItemRequest {

    private Long codigoProduto;

    private String nomeProduto;

    private Integer quantidade;

    private Double precoUnitario;

    private String tipoProduto;
}
