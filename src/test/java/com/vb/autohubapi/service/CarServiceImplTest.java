package com.vb.autohubapi.service;

import com.vb.autohubapi.middleware.restservices.domain.car.CarCreateResponseDTO;
import com.vb.autohubapi.middleware.restservices.domain.car.CarEntity;
import com.vb.autohubapi.middleware.restservices.domain.car.CarUpdateResponseDTO;
import com.vb.autohubapi.middleware.restservices.mysql.CarRepository;
import com.vb.autohubapi.middleware.restservices.service.impl.CarServiceImpl;
import com.vb.autohubapi.middleware.restservices.util.CarUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CarServiceImplTest {

    /*Passos para criar um teste:
    * Arrange;
    * Action;
    * Assert;
    */

    @Mock // p/ classes ou interfaces que são colaboradoras ou dependências da classe que está sendo testada.
    private CarRepository mockCarRepository;

    private CarEntity mockCarEntity;

    @Mock
    private CarCreateResponseDTO carCreateResponseDTO;

    @Mock
    private CarUpdateResponseDTO carUpdateResponseDTO;

    @Mock
    CarUtil carUtil;

    @InjectMocks // p/ indicar que os mocks criados com @Mock devem ser injetados na instância dessa classe.
    private CarServiceImpl mockServiceImpl;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockCarEntity = new CarEntity();
        startCarEntity();

        carCreateResponseDTO = new CarCreateResponseDTO();
        startResponseCreate();

        carUpdateResponseDTO = new CarUpdateResponseDTO();
        startResponseUpdate();
    }


    @Test
    @DisplayName("Save car with Success")
    void when_saveCarIsWithSuccess() throws Exception {
        // Configuração do comportamento do método save do mockCarRepository
        // Configura que ao chamar o método save do mockCarRepository com qualquer instância de CarEntity,
        // ele deve retornar o mockCarEntity
        when(mockCarRepository.save(Mockito.any(CarEntity.class))).thenReturn(mockCarEntity);
        when(carUtil.buildCreateCarCreateResponseObject(Mockito.any(CarEntity.class))).thenReturn(carCreateResponseDTO);

        // Executando o método do serviço que será testado
        // Chama o método saveCar do mockCarServiceImpl passando o mockCarEntity e recebe o resultado em savedCar
        CarCreateResponseDTO savedCar = mockServiceImpl.saveCar(mockCarEntity);

        // Verificando se o método save do mockCarRepository foi chamado exatamente uma vez
        // Garante que o método save do mockCarRepository foi chamado uma vez com qualquer instância de CarEntity
        verify(mockCarRepository, times(1)).save(Mockito.any(CarEntity.class));


        assertNotNull(savedCar);
        assertEquals(mockCarEntity.getId(), savedCar.getCarId());
        assertEquals(mockCarEntity.getPlaca(), savedCar.getPlaca());
        assertNotNull(savedCar.getCreatedDate());// Verificando se o campo CreatedDate foi preenchido
    }

    @Test
    @DisplayName("Exception test for checkFabricationYear")
    void when_fabricationYearIsUnderRule() {

        // Arrange
        mockCarEntity.setYear(2014); //input de ano inválido, gera excecao para o teste

        when(mockCarRepository.save(Mockito.any(CarEntity.class))).thenReturn(mockCarEntity);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            mockServiceImpl.saveCar(mockCarEntity);
        });

    }

    @Test
    @DisplayName("when_getAllCarsActiveTrueReturnWhereActiveIsTrue")
    void when_getAllCarsActiveTrue() {

        CarEntity activeCar = new CarEntity();
        activeCar.setActive(true);

        List<CarEntity> activeCarList = new ArrayList<>();
        activeCarList.add(activeCar);

        when(mockServiceImpl.getAllCarsActiveTrue()).thenReturn(activeCarList);
        assertEquals(1, activeCarList.size());
        assertTrue(activeCarList.get(0).isActive());

    }

    @Test
    @DisplayName("Should return updated car response when all arguments are valid")
    void when_updateCar_works() {

        when(mockCarRepository.findById(mockCarEntity.getId())).thenReturn(Optional.ofNullable(mockCarEntity));
        when(mockServiceImpl.updateCarInDataBase(mockCarEntity.getId(),mockCarEntity)).thenReturn(mockCarEntity);

        assertDoesNotThrow(() -> mockServiceImpl.updateCar(mockCarEntity.getId(),mockCarEntity));
    }

    @Test
    @DisplayName("Should disable car By Id")
    void when_disableCarById() {

        when(mockCarRepository.findById(mockCarEntity.getId())).thenReturn(Optional.of(mockCarEntity));
        assertDoesNotThrow(() -> mockServiceImpl.disableCarById(mockCarEntity.getId()));

    }

    @Test
    @DisplayName("Should get car By Id")
    void when_getCarById() {

        when(mockCarRepository.findById(mockCarEntity.getId())).thenReturn(Optional.of(mockCarEntity));
        assertDoesNotThrow(() -> mockServiceImpl.getCarById(mockCarEntity.getId()));

    }

    private void startCarEntity(){
        mockCarEntity.setId(1L);
        mockCarEntity.setBrand("Lamborghini");
        mockCarEntity.setModel("Aventador");
        mockCarEntity.setYear(2030);
        mockCarEntity.setPreco(450000);
        mockCarEntity.setCor("Bronze");
        mockCarEntity.setPlaca("ABC-1234");
        mockCarEntity.setActive(true);
        mockCarEntity.setQuilometragem(95000);
        mockCarEntity.setCombustivel("Gasolina");
        mockCarEntity.setTransmissao("Automatica");
        mockCarEntity.setNumPortas(4);
    }

    private void startResponseCreate() {
        carCreateResponseDTO.setCarId(mockCarEntity.getId());
        carCreateResponseDTO.setPlaca(mockCarEntity.getPlaca());
        carCreateResponseDTO.setCreatedDate(LocalDateTime.now());
    }

    private void startResponseUpdate() {
        carUpdateResponseDTO.setCarId(mockCarEntity.getId());
        carCreateResponseDTO.setPlaca(mockCarEntity.getPlaca());
        carUpdateResponseDTO.setDhUpdate(LocalDateTime.now());
    }
}