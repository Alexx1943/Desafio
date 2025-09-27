package Desafio.Santander.desafioSantander.service;

import Desafio.Santander.desafioSantander.Utilitarios.PedidoUtils;
import Desafio.Santander.desafioSantander.domain.Pedido;
import Desafio.Santander.desafioSantander.repository.PedidoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    void init(){

        pedidoUtils.listaPedidos();

    }

    @Test
    @Order(1)
    @DisplayName("findAll retorna uma lista com todos os pedidos")
    void findAllRetornaListaPedido() {

        BDDMockito.when(repository.findAll()).thenReturn(pedidoUtils.listaPedidos());

        var pedidos = service.findAll();

        Assertions.assertThat(pedidos).isNotEmpty().hasSameElementsAs(pedidoUtils.listaPedidos());
    }

}