package Desafio.Santander.desafioSantander.mapper;

import Desafio.Santander.desafioSantander.domain.Item;
import Desafio.Santander.desafioSantander.dto.request.ItemPostRequest;
import Desafio.Santander.desafioSantander.dto.request.ItemPutRequest;
import Desafio.Santander.desafioSantander.dto.response.ItemGetResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ItemMapper {

    ItemGetResponse toItemGetResponse(Item item);

    List<ItemGetResponse> toListItemGetResponse(List<Item> items);

    Item toPostItem(ItemPostRequest itemPostRequest);

    Item toPutItem(ItemPutRequest itemPutRequest);
}

