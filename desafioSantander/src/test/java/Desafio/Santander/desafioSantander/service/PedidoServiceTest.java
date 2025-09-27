package Desafio.Santander.desafioSantander.service;

import Desafio.Santander.desafioSantander.Utilitarios.PedidoUtils;
import Desafio.Santander.desafioSantander.repository.PedidoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PedidoServiceTest {


    @Mock
    private PedidoRepository repository;

    @InjectMocks
    private PedidoUtils pedidoUtils;

    @InjectMocks
    private PedidoService service;


    @BeforeEach
    void init() {

        pedidoUtils.getListaPedidos();

    }

    @Test
    @Order(1)
    @DisplayName("findAll retorna uma lista com todos os pedidos quado o argumento é null")
    void findAllRetornaListaPedido() {

        BDDMockito.when(repository.findAll()).thenReturn(Collections.emptyList());

        var pedidos = service.findAll();

        Assertions.assertThat(pedidos).isEmpty();
    }

    @Test
    @Order(2)
    @DisplayName("save cria um novo pedido")
    void saveCriaPedido() {

        var pedidoToSave = pedidoUtils.getPedido();

        BDDMockito.when(repository.save(pedidoToSave)).thenReturn(pedidoToSave);

        var pedidoSaved = service.save(pedidoToSave);

        Assertions.assertThat(pedidoSaved).isEqualTo(pedidoSaved);
    }

    @Test
    @Order(3)
    @DisplayName("findById retorna uma lista com o pedido")
    void findByIdRetornaListaPedidoId() {

        var id = pedidoUtils.getPedidos().getFirst();

        BDDMockito.when(repository.findById(id.getId())).thenReturn(Optional.of(pedidoUtils.getPedido()));

        var pedidoId = service.findById(id.getId());

        Assertions.assertThat(pedidoId.getId()).isEqualTo(id.getId());
    }


    @Test
    @Order(4)
    @DisplayName("findById lança ResponseStatusException quando o id não existe")
    void findByIdThrowsResponseStatusException_whenArguentIsNotExist() {

        var id = 90L;

        BDDMockito.when(repository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThatException().isThrownBy(() -> service.findById(id))
                .isInstanceOf(ResponseStatusException.class);
    }



}