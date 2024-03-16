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
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CarServiceTest {

    /*Passos para criar um teste:
    * Arrange;
    * Action;
    * Assert;
    */

    @Mock // p/ classes ou interfaces que são colaboradoras ou dependências da classe que está sendo testada.
    private CarRepository mockCarRepository;

    @InjectMocks // p/ indicar que os mocks criados com @Mock devem ser injetados na instância dessa classe.
    private CarService mockCarService;

    @InjectMocks
    private CarEntity mockCarEntity;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startCarEntity();
    }


    @Test
    @DisplayName("Save car with Success")
    void when_saveCarIsWithSuccess() {
        // Configuração do comportamento do método save do mockCarRepository
        // Configura que ao chamar o método save do mockCarRepository com qualquer instância de CarEntity,
        // ele deve retornar o mockCarEntity
        when(mockCarRepository.save(Mockito.any(CarEntity.class))).thenReturn(mockCarEntity);

        // Executando o método do serviço que será testado
        // Chama o método saveCar do mockCarService passando o mockCarEntity e recebe o resultado em savedCar
        CarEntity savedCar = mockCarService.saveCar(mockCarEntity);

        // Verificando se o método save do mockCarRepository foi chamado exatamente uma vez
        // Garante que o método save do mockCarRepository foi chamado uma vez com qualquer instância de CarEntity
        verify(mockCarRepository, times(1)).save(Mockito.any(CarEntity.class));

        assertNotNull(savedCar);//certifica que a resposta não é um null
        // Verificando se o método saveCar do serviço retorna a entidade carro mockada
        // Garante que o resultado retornado pelo método saveCar do mockCarService é o mesmo mockCarEntity
        assertSame(mockCarEntity, savedCar);
    }

    @Test
    @DisplayName("Exception test for checkFabricationYear")
    void when_fabricationYearIsUnderRule() {

        when(mockCarRepository.save(Mockito.any(CarEntity.class))).thenReturn(mockCarEntity);

        try {
            mockCarEntity.setAno(1995); //input de valor que gera excecao para o teste
            CarEntity savedCar = mockCarService.saveCar(mockCarEntity);
        } catch (RuntimeException runtimeException){
            assertEquals(RuntimeException.class, runtimeException.getClass());
        }
    }

    private void startCarEntity(){
        mockCarEntity.setMarca("Lamborghini");
        mockCarEntity.setModelo("Aventador");
        mockCarEntity.setAno(2030);
        mockCarEntity.setPreco(450000);
        mockCarEntity.setCor("Bronze");
        mockCarEntity.setPlaca("ABC-1234");
        mockCarEntity.setActive(true);
    }
}