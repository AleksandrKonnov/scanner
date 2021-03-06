# FS Scanner

### Используемые технологии
* Java 11
* Spring Boot 2.7.0
* Spring MVC
* Spring Data JPA
* Hibernate
* Mustache
* Lombok
* Slf4j
* Docker 4.7.1
* PostgreSQL 14.3
* Apache Tomcat 9.0.63
* Gradle

### Разворачивание приложения на сервере
1. установите Apache Tomcat
2. установите PostgreSQL:
   * при использовании Docker из каталога проекта `\docker` запустите  команду `docker-compose -f postgresql.yml up` 
   * при установке из дистрибутива создайте новую БД `scanner` (с помощью pgAdmin, DBeaver или psql)
3. задеплойте приложение на сервер:
   * скопируйте WAR-файл в каталог `\webapps` Tomcat 
   * или воспользуйтесь Tomcat Web Application Manager 
4. измените настройки приложения в `application.properties`, каталог `\webapps\scanner\WEB-INF\classes`
   * scanner.startup-scan-path - стартовый каталог для сканирования файлов
   * scanner.parallel-thread-count - количество потоков

### Работа приложения
* после запуска приложения откройте [http://localhost:8080/](http://localhost:8080/)
* для поиска файлов по имени введите имя файла в поле ввода и нажмите кнопку `Filter` 
  * если поле ввода пустое, то отобразятся все файлы
* логи приложения находятся в каталоге Tomcat `\logs\scanner.log`

### Ограничения и допущения
* настройка приложения производится через файл `application.properties`
* сканирование файлов выполняется один раз во время запуска приложения
* если во время сканирования возникает ошибка, то она записывается в лог и приложение не запускается
* в результатах сканирования имя файла содержит расширение файла
* в БД в таблицу результатов сканирования в тестовых целях добавлена колонка `absolute_path`
* юнит тесты отсутствуют