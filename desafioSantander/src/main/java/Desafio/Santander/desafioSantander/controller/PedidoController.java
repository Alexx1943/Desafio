package Desafio.Santander.desafioSantander.controller;


import Desafio.Santander.desafioSantander.domain.Pedido;
import Desafio.Santander.desafioSantander.dto.request.PedidoPostRequest;
import Desafio.Santander.desafioSantander.dto.request.PedidoPutRequest;
import Desafio.Santander.desafioSantander.dto.response.PedidoGetResponse;
import Desafio.Santander.desafioSantander.mapper.PedidoMapper;
import Desafio.Santander.desafioSantander.service.PedidoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/pedidos")
@RequiredArgsConstructor
@Slf4j
public class PedidoController {

    private final PedidoService service;
    private final PedidoMapper mapper;


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PedidoGetResponse> save(@RequestBody PedidoPostRequest pedidoPostRequest) {
        log.info("Criar pedido '{}'", pedidoPostRequest);

        var request = mapper.toPostPedido(pedidoPostRequest);

        var save = service.save(request);

        var response = mapper.toPedidoGetResponse(save);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping()
    public ResponseEntity<List<PedidoGetResponse>> findAll(@RequestParam(required = false) String pedidos) {
        log.info("Buscar todos os pedidos '{}", pedidos);

        var pedido = service.findAll();

        var response = mapper.toListPedidoGetResponse(pedido);

        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<PedidoGetResponse> findById(@PathVariable Long id) {
        log.info("Buscar pedidos por ID '{}", id);

        var byId = service.findById(id);

        var response = mapper.toPedidoGetResponse(byId);

        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody PedidoPutRequest pedidoPutRequest){
        log.info("Atualiza pedido id");

        var pedidoAtualizado = mapper.toPutPedido(pedidoPutRequest);

        service.update(id, pedidoAtualizado);

        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Excluir pedido por ID '{}", id);
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteTodos() {
        log.info("Excluir todos pedidos");

        service.deleteAll();

        return ResponseEntity.noContent().build();
    }


}
