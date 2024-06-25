package br.ada.caixa.service.cliente;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import br.ada.caixa.dto.request.RegistrarClientePFRequestDto;
import br.ada.caixa.dto.request.RegistrarClientePJRequestDto;
import br.ada.caixa.dto.response.ClienteResponseDto;
import br.ada.caixa.dto.response.RegistrarClienteResponseDto;
import br.ada.caixa.entity.Cliente;
import br.ada.caixa.entity.TipoCliente;
import br.ada.caixa.respository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

public class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegistrarPF() {
        RegistrarClientePFRequestDto requestDto = new RegistrarClientePFRequestDto();
        Cliente cliente = new Cliente();
        when(modelMapper.map(requestDto, Cliente.class)).thenReturn(cliente);
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        RegistrarClienteResponseDto response = clienteService.registrarPF(requestDto);

        assertNotNull(response);
        assertEquals(cliente.getDocumento(), response.getDocumento());
    }

    @Test
    public void testRegistrarPJ() {
        RegistrarClientePJRequestDto requestDto = new RegistrarClientePJRequestDto();
        Cliente cliente = new Cliente();
        when(modelMapper.map(requestDto, Cliente.class)).thenReturn(cliente);
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        RegistrarClienteResponseDto response = clienteService.registrarPJ(requestDto);

        assertNotNull(response);
        assertEquals(cliente.getDocumento(), response.getDocumento());
    }

    @Test
    public void testListarTodosTipoCliente() {
        Cliente cliente1 = new Cliente();
        cliente1.setTipo(TipoCliente.PF);
        Cliente cliente2 = new Cliente();
        cliente2.setTipo(TipoCliente.PF);

        when(clienteRepository.findAllByTipo(TipoCliente.PF)).thenReturn(Arrays.asList(cliente1, cliente2));

        ClienteResponseDto responseDto1 = new ClienteResponseDto();
        responseDto1.setTipo(TipoCliente.PF.name());
        ClienteResponseDto responseDto2 = new ClienteResponseDto();
        responseDto2.setTipo(TipoCliente.PF.name());

        when(modelMapper.map(cliente1, ClienteResponseDto.class)).thenReturn(responseDto1);
        when(modelMapper.map(cliente2, ClienteResponseDto.class)).thenReturn(responseDto2);

        List<ClienteResponseDto> response = clienteService.listarTodos(TipoCliente.PF);

        assertNotNull(response);
        assertEquals(2, response.size());
        assertEquals(TipoCliente.PF.name(), response.get(0).getTipo());
        assertEquals(TipoCliente.PF.name(), response.get(1).getTipo());
    }

    @Test
    public void testListarTodos() {
        Cliente cliente1 = new Cliente();
        cliente1.setTipo(TipoCliente.PF);
        Cliente cliente2 = new Cliente();
        cliente2.setTipo(TipoCliente.PJ);

        when(clienteRepository.findAll()).thenReturn(Arrays.asList(cliente1, cliente2));

        ClienteResponseDto responseDto1 = new ClienteResponseDto();
        responseDto1.setTipo(TipoCliente.PF.name());
        ClienteResponseDto responseDto2 = new ClienteResponseDto();
        responseDto2.setTipo(TipoCliente.PJ.name());

        when(modelMapper.map(cliente1, ClienteResponseDto.class)).thenReturn(responseDto1);
        when(modelMapper.map(cliente2, ClienteResponseDto.class)).thenReturn(responseDto2);

        List<ClienteResponseDto> response = clienteService.listarTodos();

        assertNotNull(response);
        assertEquals(2, response.size());
        assertEquals(TipoCliente.PF.name(), response.get(0).getTipo());
        assertEquals(TipoCliente.PJ.name(), response.get(1).getTipo());
    }
}
