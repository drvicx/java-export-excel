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
  
- IDE:<br>
  IntelliJ IDEA 2020.3 <br>

- LINKS:<br>
  (1) [JXLS - A small Java library to make generation of Excel reports easy](http://jxls.sourceforge.net/index.html) <br>
  (2) [GitHub - Andress Sacco - Example of JXLS](https://github.com/andres-sacco/example-jxls) <br>
  (3) [GitHub - Java Faker - This library generates fake data](https://github.com/DiUS/java-faker) <br>
  (4) [Baeldung - A Guide to JavaFaker](https://www.baeldung.com/java-faker) <br>
  <br>

**=CHANGE LOG**<br>
*новые записи в начале <br>

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

- App v2.0 -- Excel шаблон - timesheetTemplate.xls <br>
  ![clientsTemplate_xls](_preview/v20_timesheetTemplate.png?raw=true)
  <br><br>

- App v2.0 -- Сформированный Excel отчет - timesheet.xls <br>
  ![clients_xls](_preview/v20_timesheet.png?raw=true)
  <br><br><br>  

- App v1.0 -- Excel шаблон - clientsTemplate.xls <br>
  ![clientsTemplate_xls](_preview/v10_clientsTemplate.png?raw=true)
  <br><br>

- App v1.0 -- Сформированный Excel отчет - clients.xls <br>
  ![clients_xls](_preview/v10_clients.png?raw=true)
