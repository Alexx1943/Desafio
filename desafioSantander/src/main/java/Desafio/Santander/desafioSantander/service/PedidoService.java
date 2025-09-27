package Desafio.Santander.desafioSantander.service;

import Desafio.Santander.desafioSantander.domain.Item;
import Desafio.Santander.desafioSantander.domain.Pedido;
import Desafio.Santander.desafioSantander.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor
@Service
public class PedidoService {

    private final PedidoRepository repository;


    public List<Pedido> findAll() {

        return repository.findAll();
    }

    public Pedido findById(Long id) {

        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
    }


    public Pedido save(Pedido pedido) {
        if (pedido.getListPedidos() != null) {
            pedido.getListPedidos().forEach(item -> item.setPedido(pedido));
            pedido.getListPedidos().forEach(item -> item.setCodigoProduto(ThreadLocalRandom.current().nextLong(100)));
            pedido.setValorTotal(valorTotal(pedido.getListPedidos()));
        } else {
            pedido.setValorTotal(0.0);
        }

        pedido.setDataHoraCriacao(LocalDateTime.now());
        pedido.setDataHoraAtualizacao(LocalDateTime.now());
        pedido.setNumeroPedido(ThreadLocalRandom.current().nextLong(1000));

        return repository.save(pedido);
    }


    public void delete(Long id) {

        var pedidoDelete = findById(id);

        repository.delete(pedidoDelete);
    }

    public void deleteAll() {

        repository.deleteAll();
    }

    public void update(Long id, Pedido pedido) {

        var pedidoAlterado = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));

        pedidoAlterado.setNumeroPedido(pedido.getNumeroPedido());
        pedidoAlterado.setCanal(pedido.getCanal());
        pedidoAlterado.setDocumentoCliente(pedido.getDocumentoCliente());
        pedidoAlterado.setGerente(pedido.getGerente());
        pedidoAlterado.setStatus(pedido.getStatus());
        pedidoAlterado.setTipoDocumento(pedido.getTipoDocumento());

        pedidoAlterado.getListPedidos().clear();
        if (pedido.getListPedidos() != null) {
            pedido.getListPedidos().forEach(item -> {
                item.setPedido(pedidoAlterado);
                pedidoAlterado.getListPedidos().add(item);
            });
        }

        pedidoAlterado.setValorTotal(valorTotal(pedidoAlterado.getListPedidos()));

        pedidoAlterado.setDataHoraAtualizacao(LocalDateTime.now());



        repository.save(pedidoAlterado);
    }

    public double valorTotal(List<Item> items) {
        if (items == null) return 0;
        return items.stream()
                .mapToDouble(item -> item.getPrecoUnitario() * item.getQuantidade())
                .sum();
    }


}
