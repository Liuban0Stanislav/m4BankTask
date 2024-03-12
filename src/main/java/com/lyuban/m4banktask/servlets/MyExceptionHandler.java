package com.lyuban.m4banktask.servlets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.lyuban.m4banktask.servlets.MethodInspector.getCurrentClassName;
import static com.lyuban.m4banktask.servlets.MethodInspector.getCurrentMethodName;

/**
 * Класс обработчик исключений, для гибкой настройки JSON ответов.
 *
 * @Версия: 1.0
 * @Дата: 10.03.2024
 * @Автор: Станислав Любань
 */
@Slf4j
@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {
    @Data
    @AllArgsConstructor
    private static class ExceptionDTO {
        private int code;
        private String message;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ExceptionDTO> handleIllegalArgumentException() {
        log.info("вызван метод сервиса " + getCurrentClassName() + ": " + getCurrentMethodName());
        return new ResponseEntity<>(new ExceptionDTO(
                400,
                "Удаляемый объект не должен быть равен null."), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(OptimisticLockingFailureException.class)
    protected ResponseEntity<ExceptionDTO> handleOptimisticLockingFailureException() {
        log.info("вызван метод сервиса " + getCurrentClassName() + ": " + getCurrentMethodName());
        return new ResponseEntity<>(new ExceptionDTO(
                409,
                "Конфликт версий: версия сущности, хранящаяся в базе данных," +
                        " не соответствует версии, считанной из базы данных."), HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<ExceptionDTO> handleDataIntegrityViolationException() {
        log.info("вызван метод сервиса " + getCurrentClassName() + ": " + getCurrentMethodName());
        return new ResponseEntity<>(new ExceptionDTO(
                409,
                "Повторяющееся значение ключа нарушает ограничение уникальности:" +
                        " модель с таким именем уже есть в базе данных."), HttpStatus.CONFLICT
        );
    }
}