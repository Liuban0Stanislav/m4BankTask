package com.lyuban.m4banktask.serviсes.Impl;

import com.lyuban.m4banktask.DAO.MyJPACriteria;
import com.lyuban.m4banktask.DTO.RemoveRequestDTO;
import com.lyuban.m4banktask.DTO.ResponseDTO;
import com.lyuban.m4banktask.DTO.SumRequestDTO;
import com.lyuban.m4banktask.DTO.SumResponseDTO;
import com.lyuban.m4banktask.models.Model;

import com.lyuban.m4banktask.serviсes.ModelService;
import javax.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.lyuban.m4banktask.servlets.MethodInspector.getCurrentClassName;
import static com.lyuban.m4banktask.servlets.MethodInspector.getCurrentMethodName;

/**
 * Класс-сервис содержащий бизнес логику для работы с данными сущности {@link Model}.
 *
 * @Версия: 1.0
 * @Дата: 10.03.2024
 * @Автор: Станислав Любань
 */
@Slf4j
@Service
public class ModelServiceImpl implements ModelService {
    private final MyJPACriteria myJPACriteria;

    public ModelServiceImpl(MyJPACriteria myJPACriteria) {
        this.myJPACriteria = myJPACriteria;
    }

    public void getAll(){
        myJPACriteria.getAll();
    }

    /**
     * Метод добавления пары имя-значение в БД.
     *
     * @param model объект {@link Model}
     * @return объект {@link Model}
     */
    @Override
    public ResponseDTO add(Model model) {
        log.info("вызван метод сервиса " + getCurrentClassName() + ": " + getCurrentMethodName());

        //проверка на пустую строку имени или строку, содержащую только пробелы
        if (model.getName().matches("^\\s*$")) {
            return new ResponseDTO(400, "Нельзя вводить пустую строку");
        }

        //сохраняем модель в базу данных
        //если из метода save() вернулась такая же сущность как и параметр метода, то возвращаем ДТО с ответом ok
        if (myJPACriteria.save(model).equals(model)) {
            return new ResponseDTO(200, "ok");
        }
        return new ResponseDTO(0, "По умолчанию");
    }

    /**
     * Метод удаления пары имя-значение из БД по имени.
     *
     * @param rrDTO форма {@link RemoveRequestDTO} содержащая имя для поиска модели {@link Model}
     * @returs: {@link ResponseDTO}
     */
    @Override
    public ResponseDTO remove(RemoveRequestDTO rrDTO) {
        log.info("вызван метод сервиса " + getCurrentClassName() + ": " + getCurrentMethodName());

        //извлекаем имя из ДТО
        String name = rrDTO.getName();

        //удаляем модель gпо ее имени
        if (myJPACriteria.delete(name)) {
            return new ResponseDTO(200, "ok");
        } else {
            return null;
        }
    }
//
//    /**
//     * Метод получения суммы двух чисел, идентифицируемых их именами.
//     *
//     * @param srDTO форма {@link SumRequestDTO} содержащая первое и второе слагаемые.
//     * @return форма содержащая сумму, код ответа и описание этого кода {@link SumResponseDTO}.
//     */
//    @Override
//    public SumResponseDTO sum(SumRequestDTO srDTO) {
//        log.info("вызван метод сервиса " + getCurrentClassName() + ": " + getCurrentMethodName());
//
//        //Делаем проверку слагаемых на null, если хоть одно из них null, то возвращаем соответсвующий код
//        if (srDTO.getFirst().matches("^\\s*$") && modelRepository.findByName(srDTO.getFirst()) == null ||
//                srDTO.getSecond().matches("^\\s*$") && modelRepository.findByName(srDTO.getSecond()) == null) {
//            return new SumResponseDTO(-1, 400, "Отсутствует одно из слагаемых");
//        }
//        log.info("Пройдено первое условие");
//        //Проверка, если введено неверное имя слагаемого
//        if (srDTO.getFirst() != null && modelRepository.findByName(srDTO.getFirst()) == null ||
//                srDTO.getSecond() != null && modelRepository.findByName(srDTO.getSecond()) == null) {
//            return new SumResponseDTO(-1, 400, "Имя слагаемого введено не верно.");
//        }
//        log.info("Пройдено второе условие");
//
//        //Извлекаем из ДТО поля name и ищем по ним значения в БД
//        Integer first = modelRepository.findByName(srDTO.getFirst()).getValue();
//        log.info("первое слагаемое: {}", first);
//        Integer second = modelRepository.findByName(srDTO.getSecond()).getValue();
//        log.info("Второе слагаемое: {}", second);
//        int rez = first + second;
//        log.info("Результат сложения: {}", rez);
//        return new SumResponseDTO(rez, 200, "ok");
//    }
//
//    /**
//     * Метод для получения всех моделей из БД.
//     *
//     * @return список моделей
//     */
//    public List<Model> getAll() {
//        return modelRepository.findAll();
//    }
}