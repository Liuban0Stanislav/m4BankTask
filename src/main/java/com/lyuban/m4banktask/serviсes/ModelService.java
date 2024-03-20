package com.lyuban.m4banktask.serviсes;

import com.lyuban.m4banktask.DTO.RemoveRequestDTO;
import com.lyuban.m4banktask.DTO.ResponseDTO;
import com.lyuban.m4banktask.DTO.SumRequestDTO;
import com.lyuban.m4banktask.DTO.SumResponseDTO;
import com.lyuban.m4banktask.models.Model;

import java.io.IOException;
import java.util.List;

public interface ModelService {

    void getAll();
    /**
     * Метод добавления пары имя-значение в БД.
     * @param model объект {@link Model}
     * @return объект {@link Model}
     */
    ResponseDTO add(Model model);

    /**
     * Метод удаления пары имя-значение из БД по имени.
     * @param rrDTO форма {@link RemoveRequestDTO} содержащая имя для поиска модели {@link Model}
     * @returs: {@link RemoveRequestDTO}
     */
    ResponseDTO remove(RemoveRequestDTO rrDTO) throws IOException;

//
//    /**
//     * Метод получения суммы двух чисел, идентифицируемых их именами.
//     * @param srDTO форма {@link SumRequestDTO} содержащая первое и второе слагаемые.
//     * @return форма содержащая сумму, код ответа и описание этого кода {@link SumResponseDTO}.
//     */
//    SumResponseDTO sum(SumRequestDTO srDTO);
//
//    /**
//     * Метод для получения всех моделей из БД.
//     * @return список моделей
//     */
//    List<Model> getAll();
}
