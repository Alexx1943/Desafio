package Desafio.Santander.desafioSantander.dto.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ItemGetResponse {


    private Long id;

    private Long codigoProduto;

    private String nomeProduto;

    private Integer quantidade;

    private Double precoUnitario;

    private String tipoProduto;

}
