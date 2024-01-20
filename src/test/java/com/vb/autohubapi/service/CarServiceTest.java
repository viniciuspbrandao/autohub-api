package com.vb.autohubapi.service;

import com.vb.autohubapi.domain.CarEntity;
import com.vb.autohubapi.repository.CarRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class CarServiceTest {

    @Mock
    private CarRepository mockCarRepository;

    @InjectMocks
    private CarService mockCarService;

    @InjectMocks
    private CarEntity mockCarEntity;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Car save with Success")
    void when_saveCarIsWithSucess() {
        // Configuração do comportamento do método save do mockCarRepository
        // Configura que ao chamar o método save do mockCarRepository com qualquer instância de CarEntity,
        // ele deve retornar o mockCarEntity
        Mockito.when(mockCarRepository.save(Mockito.any(CarEntity.class))).thenReturn(mockCarEntity);

        // Executando o método do serviço que será testado
        // Chama o método saveCar do mockCarService passando o mockCarEntity e recebe o resultado em savedCar
        mockCarEntity.setAno(2030); //mockando o ano para passar na validacao
        CarEntity savedCar = mockCarService.saveCar(mockCarEntity);

        // Verificando se o método save do mockCarRepository foi chamado exatamente uma vez
        // Garante que o método save do mockCarRepository foi chamado uma vez com qualquer instância de CarEntity
        verify(mockCarRepository, times(1)).save(Mockito.any(CarEntity.class));

        // Verificando se o método saveCar do serviço retorna a entidade carro mockada
        // Garante que o resultado retornado pelo método saveCar do mockCarService é o mesmo mockCarEntity
        assertSame(mockCarEntity, savedCar);
    }

    @Test
    void getAllActiveTrue() {
    }

    @Test
    void updateCar() {
    }

    @Test
    void disableCarById() {
    }

    @Test
    void getCarById() {
    }
}