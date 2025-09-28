package Desafio.Santander.desafioSantander.controller;

import Desafio.Santander.desafioSantander.Utilitarios.FileUtils;
import Desafio.Santander.desafioSantander.Utilitarios.PedidoUtils;
import Desafio.Santander.desafioSantander.domain.Pedido;
import Desafio.Santander.desafioSantander.repository.PedidoRepository;
import Desafio.Santander.desafioSantander.service.PedidoService;
import org.junit.jupiter.api.*;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@WebMvcTest(controllers = PedidoController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ComponentScan(basePackages = "Desafio.Santander.desafioSantander")
class PedidoControllerTest {

    private static final String URL = "/v1/pedidos";

    @MockitoBean
    private PedidoRepository repository;

    @MockitoBean
    private PedidoService service;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PedidoUtils pedidoUtils;

    @Autowired
    private FileUtils fileUtils;

    @Test
    @Order(1)
    @DisplayName("POST/v1/pedidos create a pedido")
    void saveCreatePedido_WhenSucessful() throws Exception {

        var pedidoToSave = pedidoUtils.getPedido();

        BDDMockito.when(service.save(pedidoToSave)).thenReturn(pedidoToSave);

        var request = fileUtils.readResourceFile("pedido/post/post-save-201.json");

        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @Order(2)
    @DisplayName("GET/v1/pedidos returns a list with all pedidos when argument is null")
    void FindAllReturnListWithAllPedidos_WhenArgumentIsNull() throws Exception {

        var listaPedidos = pedidoUtils.getListaPedidos();

        BDDMockito.when(service.findAll()).thenReturn(listaPedidos);

        var requestFindAll = fileUtils.readResourceFile("pedido/get/get-findAll-200.json");

        mockMvc.perform(MockMvcRequestBuilders.get(URL))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(requestFindAll));

    }

    @Test
    @Order(3)
    @DisplayName("GET/v1/pedidos/1 returns a list with pedido")
    void findByIdReturnsLiztWithPedido() throws Exception {

        var id = 1L;

        BDDMockito.when(service.findById(id)).thenReturn(pedidoUtils.getPedido());
        var requestById = fileUtils.readResourceFile("pedido/get/get-findById-200.json");

        mockMvc.perform(MockMvcRequestBuilders.get(URL + "/{id}", id))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(requestById));
    }

    @Test
    @Order(4)
    @DisplayName("GET/v1/90 throws ResponseStatusException wwhen id is not found")
    void findByIdThrowsResponseStatusException_WhenIdIsNotFound() throws Exception {

        var id = 99L;

        BDDMockito.when(service.findById(id)).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));

        mockMvc.perform(MockMvcRequestBuilders.get(URL + "/{id}", id))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.status().reason("Pedido não encontrado"));
    }


    @Test
    @Order(5)
    @DisplayName("PUT/v1/{id} update a pedido")
    void updateUpdatePedido() throws Exception {

        var id = pedidoUtils.getPedido();

        id.setGerente("ownwro");

        BDDMockito.when(service.findById(id.getId())).thenReturn(id);
        BDDMockito.when(service.save(id)).thenReturn(id);


        var requesUpdated = fileUtils.readResourceFile("pedido/put/put-update-200.json");

        mockMvc.perform(MockMvcRequestBuilders.put(URL + "/{id}", id.getId())
                        .content(requesUpdated)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent());

    }

    @Test
    @Order(6)
    @DisplayName("PUT/v1/{id} throws ResponseStatusException when argument is not found")
    void uupdateThrowsResponseStatusException_WhenArgumentIsNotFound() throws Exception {

        var id = 99L;

    }

    @Test
    @Order(7)
    @DisplayName("DELETE/v1/{id} delete remove a pedido")
    void deleteRemovePedido() throws Exception{

        var id = pedidoUtils.getPedido();

        BDDMockito.when(service.findById(id.getId())).thenReturn(id);
        BDDMockito.doNothing().when(service).delete(id.getId());

        mockMvc.perform(MockMvcRequestBuilders.delete(URL + "/{id}", id.getId()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    @Order(8)
    @DisplayName("DELETE/v1/99 ResponseStatusException")
    void deleteThrowsResponseStatusException(){
        var id = 99L;
    }

    @Test
    @Order(9)
    @DisplayName("DELETE/v1 remove all pedidos")
    void DeleteTodosRemoveAllPedidos() throws Exception{

        var listaPedidos = pedidoUtils.getListaPedidos();

        BDDMockito.doNothing().when(service).deleteAll();

        mockMvc.perform(MockMvcRequestBuilders.delete(URL))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent());

    }


}