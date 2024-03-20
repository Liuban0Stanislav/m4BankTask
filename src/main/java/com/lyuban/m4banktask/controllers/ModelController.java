package com.lyuban.m4banktask.controllers;

import com.lyuban.m4banktask.DTO.RemoveRequestDTO;
import com.lyuban.m4banktask.DTO.ResponseDTO;
import com.lyuban.m4banktask.DTO.SumRequestDTO;
import com.lyuban.m4banktask.DTO.SumResponseDTO;
import com.lyuban.m4banktask.models.Model;
import com.lyuban.m4banktask.serviсes.ModelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static com.lyuban.m4banktask.servlets.MethodInspector.getCurrentClassName;
import static com.lyuban.m4banktask.servlets.MethodInspector.getCurrentMethodName;

/**
 * Класс-контроллер содержащий эндпоинты приложения.
 * @Версия: 1.0
 * @Дата: 10.03.2024
 * @Автор: Станислав Любань
 */
@Slf4j
@RestController
@RequestMapping
public class ModelController {
    private final ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping("/getall")
    public void getAll(){
        modelService.getAll();
    }

    /**
     * Метод добавления пары имя-значение в БД.
     * @param model объект {@link Model}
     * @return объект {@link Model}
     */
    @PostMapping("/add")
    public ResponseDTO add(@RequestBody Model model){
        log.info("вызван метод контроллера "+ getCurrentClassName() + ": " + getCurrentMethodName());
        return modelService.add(model);
    }
    /**
     * Метод удаления пары имя-значение из БД по имени.
     * @param rrDTO форма {@link RemoveRequestDTO} содержащая имя для поиска модели {@link Model}
     * @returs: {@link RemoveRequestDTO}
     */
    @PostMapping("/remove")
    public ResponseDTO remove (@RequestBody RemoveRequestDTO rrDTO) throws IOException {
        log.info("вызван метод контроллера "+ getCurrentClassName() + ": " + getCurrentMethodName());
        return modelService.remove(rrDTO);
    }
//
//    /**
//     * Метод получения суммы двух чисел, идентифицируемых их именами.
//     * @param srDTO форма {@link SumRequestDTO} содержащая первое и второе слагаемые.
//     * @return форма содержащая сумму, код ответа и описание этого кода {@link SumResponseDTO}.
//     */
//    @PostMapping("/sum")
//    public SumResponseDTO sum(@RequestBody SumRequestDTO srDTO){
//        log.info("вызван метод контроллера "+ getCurrentClassName() + ": " + getCurrentMethodName());
//        return modelService.sum(srDTO);
//    }
//    @GetMapping
//    public List<Model> getAll(){
//        return modelService.getAll();
//    }
}
