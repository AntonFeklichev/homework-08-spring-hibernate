Проект демонстрирует реализацию CRUD-репозитория и сервисного слоя вручную, без 
CrudRepository, JpaRepository и Spring Data, с использованием:

- Spring Boot
- Hibernate,через SessionFactory/Session
- Встроенной базы данных H2 
- Unit-тестирования с JUnit 5 + Mockito
- Интеграционного тестирования с H2


Как запустить проект:

1. Клонировать репозиторий:
   
   git clone https://github.com/AntonFeklichev/homework-08-spring-hibernate

2. Убедиться, что установлен Maven и Java 17.

3. Запустить приложение с профилем H2 (по умолчанию):
  
   mvn spring-boot:run


Как запустить тесты:

mvn clean test

Интеграционные тесты запускаются с H2, профилем "test".


Используемые зависимости:

- Spring Boot Starter JDBC
- Hibernate Core 6.5.2.Final
- H2 Database
- PostgreSQL Driver
- Lombok
- JUnit 5 и Mockito


Настройки Hibernate:

Настройки находятся в:
- application-h2.yml - для H2
- application-postgres.yml - для PostgreSQL
- application-test.yml - для интеграционных тестов


Переключение с H2 на PostgreSQL

1. Запустить PostgreSQL и создать базу данных "demo_db".

2. Изменить активный профиль в application.yml на 
     spring:
       profiles:
         active: postgres
  
   Или указать при запуске:
   mvn spring-boot:run -Dspring-boot.run.profiles=postgres

Настройки PostgreSQL находятся в application-postgres.yml
