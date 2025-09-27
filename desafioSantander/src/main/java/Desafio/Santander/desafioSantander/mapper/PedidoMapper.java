package Desafio.Santander.desafioSantander.mapper;


import Desafio.Santander.desafioSantander.domain.Pedido;
import Desafio.Santander.desafioSantander.dto.request.PedidoPostRequest;
import Desafio.Santander.desafioSantander.dto.request.PedidoPutRequest;
import Desafio.Santander.desafioSantander.dto.response.PedidoGetResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = ItemMapper.class)
public interface PedidoMapper {



    Pedido toPostPedido(PedidoPostRequest pedidoPostRequest);

    Pedido toPutPedido(PedidoPutRequest pedidoPutRequest);



    @Mapping(target = "listPedidos", source = "listPedidos")
    @Mapping(target = "dataHoraCriacao", source = "dataHoraCriacao")
    @Mapping(target = "dataHoraAtualizacao", source = "dataHoraAtualizacao")
    PedidoGetResponse toPedidoGetResponse(Pedido pedido);

    List<PedidoGetResponse> toListPedidoGetResponse(List<Pedido> pedidos);

}
