# 4mBank

### [Тестовое задание](https://github.com/Liuban0Stanislav/m4BankTask/blob/master/task_java.txt)

## Содержание

- [Видео](#видео)
- [Что сделано](#что-сделано)
- [Структура проекта](#структура-проекта)
- [Описание проекта](#описание-проекта)
- [Используемые технологии](#используемые-технологии)
- [Над проектом работал](#над-проектом-работал)

## Видео

https://github.com/Liuban0Stanislav/m4BankTask/assets/108601150/491e653f-9521-4f52-aab9-0cc122d8aa8f


## Что сделано
Проект выполнен с использованием SpringBoot.
В ходе работы реализованы следующие эндпоинты:

* POST - Добавить модель;
* POST - Удалить модель;
* POST - Получение суммы двух чисел, идентифицируемых их именами;
* GET - Получить все модели из базы данных.

1) В корневом каталоге проекта можно найти файл с экспортированной коллекцией запросов Postman в формате JSON: [Эндпоинты для Postman](https://github.com/Liuban0Stanislav/m4BankTask/blob/master/m4Bank.postman_collection.json)
2) Скрипт на сборку проекта находится здесь: [sum_service.jar](https://github.com/Liuban0Stanislav/m4BankTask/blob/master/build.gradle)

<br>Для запуска используйте команду: java -jar sum_service.jar</br>
<br>Для остановки проекта нажмите Ctrl + C</br>


## Структура проекта
Настоящий SpringBoot прект имеет классическую трехслойную структуру:
- слой контроллеров, содержащий эндпоинты;
- слой сервисов, содержащий бизнесс логику;
- слой DAO(Data Access Object) или же слой репозиториев;

База данных используемая в проекте PostgreSQL.

## Описание проекта
Для выполнения поставленной задачи, была создана сущность: 
* [Model](https://github.com/Liuban0Stanislav/m4BankTask/blob/master/src/main/java/com/lyuban/m4banktask/models/Model.java)

Для настройки ответов JSON и исключениями был использован класс ExceptionHandler:
* [MyExceptionHandler](https://github.com/Liuban0Stanislav/m4BankTask/blob/master/src/main/java/com/lyuban/m4banktask/servlets/MyExceptionHandler.java)

Для логирования методов используется класс MethodInspector
* [MethodInspector](https://github.com/Liuban0Stanislav/m4BankTask/blob/master/src/main/java/com/lyuban/m4banktask/servlets/MethodInspector.java)

### Ошибки обрабатываемые в ендпоинте "Добавить модель"
- "code": 400, "description": "Нельзя вводить пустую строку" - запрет ввода имен вида "", и "  ";
- "code": 409, "description": "Конфликт версий: версия сущности, хранящаяся в базе данных, не соответствует версии, считанной из базы данных. - метод
  save() может выбрасывать исключение IllegalArgumentException
- "code": 409, "description": "Конфликт версий: версия сущности, хранящаяся в базе данных, не соответствует версии, считанной из базы данных." - метод
  save() может выбрасывать исключение OptimisticLockingFailureException

### Ошибки обрабатываемые в ендпоинте "Удалить модель"
- "code": 400,
  "description": "Удаляемый объект не должен быть равен null." - появляется при вводе пустой строки либо неверного значения

### Ошибки обрабатываемые в ендпоинте "Получение суммы двух чисел, идентифицируемых их именами."
- "code": 400, "description": "Отсутствует одно из слагаемых" - появляется если ввести пустую строку вместо имени одного из слагаемых
- "code": 400, "description": "Имя слагаемого введено не верно." - появляется если ввести неверное имя слагаемого

### Ошибок нет и метод возвращает JSON
- "code": 200, "description": "ok", "sum": 4 - пример верного ответа для ендпоинта /sum
- "code": 200, "description": "ok" - пример верного ответа для ендпоинтов /add и /remove

## Используемые технологии
Проект использует следующие технологии и библиотеки:

- [Spring Boot](https://spring.io/projects/spring-boot): Фреймворк для создания веб-приложений на языке Java.
- [Hibernate](https://hibernate.org/): Фреймворк для работы с базой данных.
- [PostgreSQL](https://www.postgresql.org/): База данных для разработки.
- [Postman](https://www.postman.com/) Инструменты для работы с эндпоинтами.

## Над проектом работал
- [Любань Станислав](https://github.com/Liuban0Stanislav)
