package com.lyuban.m4banktask;

import com.lyuban.m4banktask.DTO.RemoveRequestDTO;
import com.lyuban.m4banktask.DTO.ResponseDTO;
import com.lyuban.m4banktask.DTO.SumRequestDTO;
import com.lyuban.m4banktask.DTO.SumResponseDTO;
import com.lyuban.m4banktask.models.Model;
import com.lyuban.m4banktask.repositories.ModelRepository;
import com.lyuban.m4banktask.serviсes.Impl.ModelServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ModelServiceImplTest {
    private ModelServiceImpl modelService;
    @Mock
    private ModelRepository modelRepository;

    @BeforeEach
    public void BeforeEach() {
        modelService = new ModelServiceImpl(modelRepository);
    }

    @Test
    public void addTest() {
        Model expectedModel = new Model(1, "oneTest", 1);
        ResponseDTO responseDTO = new ResponseDTO(200, "ok");
        Mockito.when(modelRepository.save(expectedModel)).thenReturn(expectedModel);
        assertEquals(responseDTO, modelService.add(expectedModel));

    }

    @Test
    public void addCode600EmptyTest() {
        ResponseDTO responseDTO = new ResponseDTO(600, "Нельзя вводить пустую строку");
        Model model = new Model(1, "", 1);
        assertEquals(responseDTO, modelService.add(model));
    }

    @Test
    public void addCode600SpaceTest() {
        ResponseDTO responseDTO = new ResponseDTO(600, "Нельзя вводить пустую строку");
        Model model = new Model(1, "   ", 1);
        assertEquals(responseDTO, modelService.add(model));
    }

    @Test
    public void removeTest() {
        Model expectedModel = new Model(1, "oneTest", 1);
        RemoveRequestDTO rrDTO = new RemoveRequestDTO();
        rrDTO.setName("oneTest");
        ResponseDTO responseDTO = new ResponseDTO(200, "ok");
        Mockito.when(modelRepository.findByName(rrDTO.getName())).thenReturn(expectedModel);
        assertEquals(responseDTO, modelService.remove(rrDTO));
    }

    @Test
    public void sumTest() {
        Model model1 = new Model(1, "oneTest", -3);
        Model model2 = new Model(2, "twoTest", 5);
        int rez = model1.getValue() + model2.getValue();

        SumRequestDTO srDTO = new SumRequestDTO();
        srDTO.setFirst("firstTest");
        srDTO.setSecond("secondTest");

        Mockito.when(modelRepository.findByName(srDTO.getFirst())).thenReturn(model1);
        Mockito.when(modelRepository.findByName(srDTO.getSecond())).thenReturn(model2);

        SumResponseDTO responseDTO = new SumResponseDTO(rez, 200, "ok");
        assertEquals(responseDTO, modelService.sum(srDTO));
    }

    @Test
    public void sumCode610Test() {
        SumRequestDTO srDTO = new SumRequestDTO();
        srDTO.setFirst("");
        srDTO.setSecond("secondTest");

        Mockito.when(modelRepository.findByName(srDTO.getFirst())).thenReturn(null);

        SumResponseDTO responseDTO = new SumResponseDTO(-1, 610, "Отсутствует одно из слагаемых");

        //проверка суммы
        int expected = responseDTO.getSum();
        int actual = modelService.sum(srDTO).getSum();
        assertEquals(expected, actual);

        //проверка кода
        int expectedCode = 610;
        assertEquals(expectedCode, modelService.sum(srDTO).getCode());

        //проверка описания
        String expectedMessage = "Отсутствует одно из слагаемых";
        assertEquals(expectedMessage, modelService.sum(srDTO).getDescription());
    }

    @Test
    public void sumCode620Test() {
        SumRequestDTO srDTO = new SumRequestDTO();
        srDTO.setFirst("firstTest");
        srDTO.setSecond("secondTest");

        Mockito.when(modelRepository.findByName(srDTO.getFirst())).thenReturn(new Model(1, "someOtherName", 3));

        SumResponseDTO responseDTO = new SumResponseDTO(-1, 620, "Имя слагаемого введено не верно.");

        //проверка суммы
        int expected = responseDTO.getSum();
        int actual = modelService.sum(srDTO).getSum();
        assertEquals(expected, actual);

        //проверка кода
        int expectedCode = 620;
        assertEquals(expectedCode, modelService.sum(srDTO).getCode());

        //проверка описания
        String expectedMessage = "Имя слагаемого введено не верно.";
        assertEquals(expectedMessage, modelService.sum(srDTO).getDescription());
    }
}
