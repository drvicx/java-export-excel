# java-export-excel
Support project for Timesheet Cloud 2.0 WebApp - Export data to Excel spreadsheets
<hr>

**=INFO**
- DEPENDENCIES:<br>
  Oracle Java JDK  17  (build 17.0.1+12-LTS-39) <br>
  Spring Boot 2.4.12 Starter + Spring Boot Data JPA <br>
  Spring Boot Maven Plugin <br>
  Jxls 2.9.0 <br>
  Java Faker 1.0.2 <br>
  HSQLDB 2.5.1 <br>
  
- IDE:<br>
  IntelliJ IDEA 2020.3 <br>

- LINKS:<br>
  (1) [JXLS - A small Java library to make generation of Excel reports easy](http://jxls.sourceforge.net/index.html) <br>
  (2) [GitHub - Andres Sacco - Example of JXLS](https://github.com/andres-sacco/example-jxls) <br>
  (3) [GitHub - Java Faker - This library generates fake data](https://github.com/DiUS/java-faker) <br>
  (4) [Baeldung - A Guide to JavaFaker](https://www.baeldung.com/java-faker) <br>
  (5) [Baeldung - Introduction to JDBC](https://www.baeldung.com/java-jdbc) <br>
  (6) [HSQLDB - 100% Java Database](http://hsqldb.org/) <br>
  (7) [HSQLDB Guide - Chapter 9. SQL-Invoked Routines](https://hsqldb.org/doc/guide/sqlroutines-chapt.html) <br>
  (8) [Baeldung - Spring Boot With JavaServer Pages (JSP)](https://www.baeldung.com/spring-boot-jsp) <br>
  (9) [Spring Boot and JSP Tutorial with Examples](https://o7planning.org/11681/spring-boot-and-jsp) <br>
  (10) [Spring Boot JSP Tutorial with Example](https://hellokoding.com/spring-boot-hello-world-example-with-jsp/) <br>

  <br>

**=CHANGE LOG**<br>
*новые записи в начале <br>

04: 20220520_0050:
<pre>
- реализован каркас Spring Boot Web MVC приложения необходимого для дальнейшей работы;
- далее необходимо будет реализовать выгрузку Excel формы Табеля из HTML-страницы веб-приложения;

- изменения отправлены в ветку "release-3.1" репозитория после чего произведено слияние с веткой "main";
- приложению присвоен тег версии 3.1 на основе которого на GitHub создан релиз v3.1;
</pre>

03: 20220516_1430:
<pre>
- реализована выборка динамических данных из БД HSQLDB с помощью хранимой процедуры для заполнения Excel-Табеля;
- в БД HSQLDВ реализованы:
  - пользовательская хранимая функция агрегации "group_concatenate" (используется при вычислении подытогов)
    собирающая результат select-запроса в строку с разделителем ","
    которая является аналогом функции stuff() из MSSQL отсутствующей в HSQLDB;
  - хранимая процедура "sp_getTimesheetDataV3(year,month)" с 2мя параметрами - Год и Месяц
    за которые извлекаются данные из таблицы [TIMEDATA], т.е. Год и Месяц Табеля;
  - таблица [TIMESHEET] для хранения результата вызова хранимой процедуры;

- в конфигурацию зависимостей проекта (pom.xml) добавлена библиотека для работы с HSQLDB через JDBC;
- создан Excel-шаблон "timesheetTemplate30_listDataV3.xls" сокращенной тестовой версии Табеля;
- созданы различные Entity и DAO-классы для тестирования вариантов формирования данных Табеля, а именно:
  - Entity-класс "TimesheetV3" описывающий
  - DAO-класс "TimesheetV3Dao" в котором происходит выборка динамических данных из БД путем вызова хранимой процедуры;
  - класс Сервисного слоя "TimesheetV3Service" в котором производится формирование Excel отчета с подключением шаблона и данных;
- внесены изменения в исполняемый класс приложения Application:
  - подключен сервисный слой "TimesheetV3Service" для формирования Excel-отчета с новой структурой данных;

- изменения отправлены в ветку "release-3.0" репозитория после чего произведено слияние с веткой "main";
- приложению присвоен тег версии 3.0 на основе которого на GitHub создан релиз v3.0;
</pre>

02: 20220225_1310:
<pre>
- реализован вывод своих тестовых данных для Табеля/Timesheet;
- созданы Excel-шаблон "timesheetTemplate.xls";
- в конфигурацию зависимостей проекта (pom.xml) добавлена библиотека Lombok 1.18.20 для упрощения описания getter/setter методов;
- созданы Entity-классы Orgdata, User, Timedata;
- создан DAO-класс TimesheetDao содержащий статические данные для вывода в отчет;
- создан класс Сервисного слоя TimesheetService в котором производится вызов Timesheet отчета с подключением шаблона;
- внесены изменения в исполняемый класс приложения Application: откл. формирование Client отчета и вкл. формирование Timesheet;

- изменения отправлены в ветку "release-2.0" репозитория после чего произведено слияние с веткой "main";
- приложению присвоен тег версии 2.0 на основе которого на GitHub создан релиз v2.0;
</pre>

01: 20220221_1610:
<pre>
- за основу взят проект (2), внесены исправления в файл pom.xml т.к код не компилировался;  
- структура проекта:

  /src
   - main/

     - java/
       - com.example.jxls/
         - common/
           Report.java
         - dao/
           CommonDao.java
         - model/
           Client.java
         - service/
           CommonService.java

         Application.java

     - resources/
       - reports/
         clientsTemplate.xls

  /target
   clients.xls

- сборка и запуск приложения:
  1. Выполнить Maven clean+compile
  2. Выполнить Run
  3. Сгенерированные данные будут инжектированы в Excel шаблон
     /src/main/resources/reports/clientsTemplate.xls
  4. Результирующий Excel файл будет сформирован в
     /target/clients.xls

- никакие исправления, относительно источника (2) в исходный код не вносились;
- изменения отправлены в ветку "release-1.0" репозитория после чего произведено слияние с веткой "main";
- приложению присвоен тег версии 1.0 на основе которого на GitHub создан релиз v1.0;
</pre>
<br>

**=APP-PREVIEW**

- App v3.1 -- Spring Boot Web MVC App - index.jsp <br>
  ![clientsTemplate_xls](_preview/v31_spring-boot-webmvc_index_jsp.png?raw=true)
  <br>

- App v3.1 -- Spring Boot Web MVC App - helloEsp.jsp <br>
  ![clientsTemplate_xls](_preview/v31_spring-boot-webmvc_hello-esp_jsp.png?raw=true)
  <br>

- App v3.1 -- Spring Boot Web MVC App - helloRus.jsp <br>
  ![clientsTemplate_xls](_preview/v31_spring-boot-webmvc_hello-rus_jsp.png?raw=true)
  <br><br><br>


- App v3.0 -- Excel шаблон - timesheetTemplate30.xls <br>
  ![clientsTemplate_xls](_preview/v30_timesheetTemplate.png?raw=true)
  <br>

- App v3.0 -- Сформированный Excel отчет - timesheet30.xls <br>
  ![clients_xls](_preview/v30_timesheet.png?raw=true)
  <br><br><br>
  
- App v2.0 -- Excel шаблон - timesheetTemplate20.xls <br>
  ![clientsTemplate_xls](_preview/v20_timesheetTemplate.png?raw=true)
  <br>

- App v2.0 -- Сформированный Excel отчет - timesheet20.xls <br>
  ![clients_xls](_preview/v20_timesheet.png?raw=true)
  <br><br><br>

- App v1.0 -- Excel шаблон - clientsTemplate.xls <br>
  ![clientsTemplate_xls](_preview/v10_clientsTemplate.png?raw=true)
  <br>

- App v1.0 -- Сформированный Excel отчет - clients.xls <br>
  ![clients_xls](_preview/v10_clients.png?raw=true)
