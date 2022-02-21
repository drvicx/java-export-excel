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

- App v1.0 -- Excel шаблон - clientsTemplate.xls <br>
  ![clientsTemplate.xls](_preview/v10_clientsTemplate.png?raw=true)
  <br><br>
  
- App v1.0 -- Сформированный Excel отчет - clients.xls <br>
  ![clients.xls](_preview/v10_clients.png?raw=true)
