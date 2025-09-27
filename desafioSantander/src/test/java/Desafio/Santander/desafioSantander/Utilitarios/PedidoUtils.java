package Desafio.Santander.desafioSantander.Utilitarios;

import Desafio.Santander.desafioSantander.domain.Item;
import Desafio.Santander.desafioSantander.domain.Pedido;
import Desafio.Santander.desafioSantander.domain.enumeracao.Canal;
import Desafio.Santander.desafioSantander.domain.enumeracao.Status;
import Desafio.Santander.desafioSantander.domain.enumeracao.TipoDocumento;
import Desafio.Santander.desafioSantander.domain.enumeracao.TipoProduto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class PedidoUtils {

    public List<Item> listaItens() {
        var produto1 = Item.builder().id(1L).codigoProduto(1L).nomeProduto("Produto1").quantidade(1).precoUnitario(1.0).tipoProduto(TipoProduto.BEBIDA).build();
        var produto2 = Item.builder().id(2L).codigoProduto(1L).nomeProduto("Produto2").quantidade(1).precoUnitario(1.0).tipoProduto(TipoProduto.COMIDA).build();
        var produto3 = Item.builder().id(3L).codigoProduto(1L).nomeProduto("Produto3").quantidade(1).precoUnitario(1.0).tipoProduto(TipoProduto.ELETRODOMESTICO).build();
        var produto4 = Item.builder().id(4L).codigoProduto(1L).nomeProduto("Produto4").quantidade(1).precoUnitario(1.0).tipoProduto(TipoProduto.ELETRONICO).build();
        var produto5 = Item.builder().id(5L).codigoProduto(1L).nomeProduto("Produto5").quantidade(1).precoUnitario(1.0).tipoProduto(TipoProduto.ROUPAS).build();

        return new ArrayList<>(List.of(produto1, produto2, produto3, produto4, produto5));
    }

    public List<Pedido> getListaPedidos() {

        var pedido1 = Pedido.builder()
                .id(1L).numeroPedido(1L)
                .tipoDocumento(TipoDocumento.CNPJ)
                .documentoCliente("CPF")
                .canal(Canal.WEB)
                .status(Status.ATIVO)
                .listPedidos(listaItens())
                .valorTotal(5.0)
                .gerente("Gerente")
                .dataHoraCriacao(LocalDateTime.now())
                .dataHoraAtualizacao(LocalDateTime.now()).build();


        var pedido2 = Pedido.builder()
                .id(1L).numeroPedido(1L)
                .tipoDocumento(TipoDocumento.CNPJ)
                .documentoCliente("CPF")
                .canal(Canal.EMAIL)
                .status(Status.INATIVO)
                .listPedidos(listaItens())
                .valorTotal(5.0)
                .gerente("Gerente")
                .dataHoraCriacao(LocalDateTime.now())
                .dataHoraAtualizacao(LocalDateTime.now()).build();

        var pedido3 = Pedido.builder()
                .id(1L).numeroPedido(1L)
                .tipoDocumento(TipoDocumento.CNPJ)
                .documentoCliente("CPF")
                .canal(Canal.TELEFONE)
                .status(Status.ATIVO)
                .listPedidos(listaItens())
                .valorTotal(5.0)
                .gerente("Gerente")
                .dataHoraCriacao(LocalDateTime.now())
                .dataHoraAtualizacao(LocalDateTime.now()).build();

        return new ArrayList<>(List.of(pedido1, pedido2, pedido3));
    }

    public List<Pedido> getPedidos() {

        var pedido = Pedido.builder()
                .id(1L).numeroPedido(1L)
                .tipoDocumento(TipoDocumento.CNPJ)
                .documentoCliente("CPF")
                .canal(Canal.WEB)
                .status(Status.ATIVO)
                .listPedidos(listaItens())
                .valorTotal(5.0)
                .gerente("Gerente")
                .dataHoraCriacao(LocalDateTime.now())
                .dataHoraAtualizacao(LocalDateTime.now()).build();

        return new ArrayList<>(List.of(pedido));
    }

    public Pedido getPedido() {

        var pedido = Pedido.builder()
                .id(1L).numeroPedido(1L)
                .tipoDocumento(TipoDocumento.CNPJ)
                .documentoCliente("CPF")
                .canal(Canal.WEB)
                .status(Status.ATIVO)
                .listPedidos(listaItens())
                .valorTotal(5.0)
                .gerente("Gerente")
                .dataHoraCriacao(LocalDateTime.now())
                .dataHoraAtualizacao(LocalDateTime.now()).build();

        return pedido;
    }

}
