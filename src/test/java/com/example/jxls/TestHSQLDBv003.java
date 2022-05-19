package com.example.jxls;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Statement;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import java.util.stream.IntStream;
import org.hsqldb.types.Types;


/* *
 * =РАБОТА С SQL-ЗАПРОСАМИ И ПОЛЬЗОВАТЕЛЬСКИМИ ФУНКЦИЯМИ HSQLDB
 *  - вызов Функции с передачей внешних значений в параметры Функции -- в Select-запросе и с помощью call
 *  - Функция при этом возвращает ЕДИНИЧНЫЙ результат типа int
 *
* */
public class TestHSQLDBv003 {

    public static void doTests() {

        //--параметры подключения
        Connection conn = null;
        String db = "jdbc:hsqldb:hsql://localhost:9000/timesheet-udm";
        String user = "SA";
        String psswd = "";

        //--Try-Catch блок
        try {
            //--создаем объект подключения
            conn = DriverManager.getConnection(db, user, psswd);


            //--пример#1 -- РАБОТАЕТ
            // =ВЫЗОВ ВСТРОЕННЫХ ФУНКЦИЙ -- из [1]
            /*
            Statement stmt = conn.createStatement();

            ResultSet r1 = stmt.executeQuery("VALUES SESSION_ID()");
            ResultSet r2 = stmt.executeQuery("VALUES SESSION_ID(), DATABASE()");
            System.out.println(r1);                                             //=OUTPUT: org.hsqldb.jdbc.JDBCResultSet@2471cca7
            System.out.println(r2);                                             //=OUTPUT: org.hsqldb.jdbc.JDBCResultSet@5fe5c6f
            */



            //--пример#2 -- РАБОТАЕТ
            // =ВЫЗОВ СВОЕЙ ФУНКЦИИ В SELECT-ЗАПРОСЕ
            /*
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select FULLNAME_ALIAS, fn_nve_getSumm(2,3) as SUMM from TIMESHEET where id = 1");
            while(rs.next()){
                System.out.println("FIO:\t"+ rs.getString("FULLNAME_ALIAS"));
                System.out.println("SUM:\t"+ rs.getString("SUMM"));
            }
            */
            //=OUTPUT:
            //      FIO:	Фунт З.П.
            //      SUM:	5



            //--пример#3 -- РАБОТАЕТ
            // =ОБРАБОТКА С 1-СТРОЧНОГО РЕЗУЛЬТАТА SELECT-ЗАПРОСА -- из [4]
            /*
            String query = "select id, userid, fullname_alias, position_rus, fn_nve_getSumm(20,7) as totalExpYears from TIMESHEET where id = 1";
            //--сначала должен быть создан объект Statement (stmt)
            try (Statement stmt = conn.createStatement()) {
                //..а уже затем объект ResultSet (rs)
                ResultSet rs = stmt.executeQuery(query);
                //..после чего Объект rs будет заполнен данными с которыми можно работать
                while(rs.next()) {
                    int recordId = rs.getInt(1);
                    int userId = rs.getInt(2);
                    String userFIO = rs.getString(3);
                    String position = rs.getString(4);
                    int yearsOfExperience = rs.getInt(5);

                    System.out.println(recordId +", "+ userId +", "+ userFIO +", "+ position +", "+ yearsOfExperience);
                }
            }
            catch (SQLException e) {
                System.err.println(e.getMessage());
            }
             */
            //=OUTPUT:
            //      1, 1, Фунт З.П., Начальник, 27



            //--пример#4 -- РАБОТАЕТ (но есть нюансы)
            //  =ОПРЕДЕЛЯЕМ ИМЕНА ПОЛЕЙ ResultSet ОБЪЕКТА -- из [5]: 6.2. ResultSetMetadata
            /*
            String query = "select id, userid, fullname_alias, position_rus, fn_nve_getSumm(20,7) as totalExpYears from TIMESHEET where id = 1";

            //..сначала должен быть создан объект Statement (stmt)
            try (Statement stmt = conn.createStatement()) {
                //..а уже затем объект ResultSet (rs)
                ResultSet rs = stmt.executeQuery(query);

                //..после чего Объект rs будет заполнен данными с которыми можно работать
                //  Проблема: но что если мы НЕ знаем какие есть поля в resultSet?
                //  Решение.: их можно получить из Метаданных resultSet Объекта
                ResultSetMetaData rsmd = rs.getMetaData();
                //..получаем счетчик колонок из метаданных
                int nrColumns = rsmd.getColumnCount();
                //..с помощью StreamAPI и функционального стиля пишем цикл выводящий Индексы и Имена полей resultSet
                IntStream.range(1, nrColumns).forEach(i -> {
                    try {
                        System.out.println(i +": "+ rsmd.getColumnName(i));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
            }
            catch (SQLException e) {
                System.err.println(e.getMessage());
            }
            */
            //=OUTPUT:
            //      1: ID
            //      2: USERID
            //      3: FULLNAME_ALIAS
            //      4: POSITION_RUS
            //                            --НЮАНС: последнее поле с алиасом выполнения Функции не вывелось..



            //--пример#5 -- РАБОТАЕТ (но есть нюанс)
            //  =ВЫЗОВ СВОЕЙ ХРАНИМОЙ ФУНКЦИЙ С ПОМОЩЬЮ CALL С ЯВНЫМ УКАЗАНИЕМ ПАРАМЕТРОВ ФУНКЦИИ
            /*
            String preparedSql = "{call fn_nve_getSumm(20,7)}";
            try (CallableStatement cstmt = conn.prepareCall(preparedSql)) {
                //..сначала выполняем callable statement (cstmt)
                cstmt.execute();
                //..затем получаем резултсет
                ResultSet rs = cstmt.getResultSet();
                //..затем переводим курсор вперед, т.к: Initially, the cursor is positioned BEFORE the first row!
                rs.next();
                //..и уже после этого мы можем считывать выходной параметр по его индексу
                System.out.println(rs.getInt(1));
            }
            */
            //=OUTPUT:
            //      27
            //



            //--пример#6 -- РАБОТАЕТ
            //  =ВЫЗОВ СВОЕЙ ХРАНИМОЙ ФУНКЦИЙ С ПОМОЩЬЮ CALL С НЕЯВНЫМ УКАЗАНИЕМ ПАРАМЕТРОВ ФУНКЦИИ -- из [5]: 4.3. CallableStatement
            //..где-то в самом начале можно установить значения входных параметров
            int in_arg1 = 30;
            int in_arg2 = 17;

            //..затем сформировать строку запроса без явного указания параметров
            String preparedSql = "{call fn_nve_getSumm(?,?)}";
            //..и уже позже в блоке попытки создания Объекта содержащего вызов Хранимой Функции..
            try (CallableStatement cstmt = conn.prepareCall(preparedSql)) {

                //..инициализировать значения параметров (по идентификаторам) строки подготовленного запроса (cstmt)
                cstmt.setInt(1, in_arg1);
                cstmt.setInt(2, in_arg2);

                //..после чего нужно выполнить "callable statement" (cstmt)
                cstmt.execute();
                //..далее получить результсет из него..
                ResultSet rs = cstmt.getResultSet();
                //..затем ОБЯЗАТЕЛЬНО перевести курсор вперед на 1 позицию, т.к: Initially, the cursor is positioned BEFORE the first row!
                rs.next();
                //..и уже после этого, можно считывать выходной параметр Функции по индексу параметра
                System.out.println(rs.getInt(1));
            }
            //=OUTPUT:
            //      47
            //


        }
        //--ПЕРЕХВАТ ОШИБКИ ПОДКЛЮЧЕНИЯ К БД (conn)
        //--в случае Ошибки перехватываем её и выводим только заголовок без полной трассировки стека ошибки (error stack trace)
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        //--в завершении - закрываем подключение с перехватом и выводом ошибки
        finally {
            try {
                if (conn != null)
                    conn.close();
            }
            catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        // Вызываем функцию запуска тестов
        doTests();
    }



    //=КОД ХРАНИМОЙ ФУНКЦИИ fn_nve_getSumm(a,b):
    /* *
     *
     * --удаляем/создаем Функцию
     *
     *      DROP FUNCTION fn_nve_getSumm
     *
     *      CREATE FUNCTION fn_nve_getSumm (IN in_varA INT, IN in_varB INT)
     *          RETURNS INT
     *          RETURN in_varA + in_varB
	 *
     * --вызываем функцию с параметрами
     *
     *      call fn_nve_getSumm(30,17)
     *
     * --получаем результат ("HSQL Database Manager", "IntelliJ Database Console")
     *
     *      @p0
     *      47
     *
     */


    //=ИСТОЧНИКИ:
    /* *
     *  1.
     *      hsqldb system functions how to use
     *      https://stackoverflow.com/questions/70109380/hsqldb-system-functions-how-to-use
     *      - есть РАБОТАЮЩИЙ пример вызова встроенной в HSQLDB хранимой функции
     * 2.
     *      JDBC HSQLDB Tutorial
     *      https://examples.javacodegeeks.com/enterprise-java/sql-enterprise-java/jdbc-hsqldb-tutorial/
     * 3.
     *      Oracle Java Tutorials | Processing SQL Statements with JDBC
     *      https://docs.oracle.com/javase/tutorial/jdbc/basics/processingsqlstatements.html
     * 4.
     *      Oracle Java Tutorials | Retrieving and Modifying Values from Result Sets -- Retrieving Column Values from Rows
     *      https://docs.oracle.com/javase/tutorial/jdbc/basics/retrieving.html
     * 5.
     *      Introduction to JDBC
     *      https://www.baeldung.com/java-jdbc
     *      - есть много примеров, но есть пример как определить имена полей resultSet когда не знаешь их
     *
     * */


    //=ОШИБКИ:
    /* *
     *  #5
     *     //------------------------------------------------------------------------------------------------------------
     *     //--РАБОТАЕТ -- ВЫЗОВ ХРАНИМЫХ ФУНКЦИЙ С ПОМОЩЬЮ CALL - есть нюанс
     *     //------------------------------------------------------------------------------------------------------------
     *     String preparedSql = "{call fn_nve_getSumm(20,7)}";
     *     try (CallableStatement cstmt = conn.prepareCall(preparedSql)) {
     *          //ResultSet rs = cstmt.getInt(1);
     *          //ResultSetMetaData rsmd = rs.getMetaData();
     *          //System.out.println(rs.getRow());                 //= 0
     *          //System.out.println(rs.getInt(1));                //= invalid cursor state: identifier cursor not positioned on row in UPDATE, DELETE, SET, or GET statement: ; ResultSet is positioned before first row
     *
     *          cstmt.execute();
     *          //cstmt.getInt(1);
     *
     *          ResultSet rs = cstmt.getResultSet();
     *          //!!!ВОТ ЭТО ВАЖНО!!!
     *          rs.next();                                //!!! написано же было в документации что по-умолчанию курсор смотрит ДО первого элемента resultSet!
     *                                                    //    https://docs.oracle.com/javase/tutorial/jdbc/basics/processingsqlstatements.html
     *                                                    //    - Processing ResultSet Objects: ..Initially, the cursor is positioned BEFORE the first row!
     *          System.out.println(rs.getInt(1));
     *     }
     *     //=OUTPUT:
     *     //      27                                    //..ЗАРАБОТАЛО.. сука блять нахуй! ..нужно было СДВИНУТЬ КУРСОР на ОДНУ позицию вперед! см. выше!
     *     //                                            //  нужно не просто уметь читать текст на английском, но еще и УМЕТЬ ПОНИМАТЬ написанное после перевода!
     *
     *
     *
     *  #5:
     *     //---МУСОР -- НАЧАЛО ---
     *     //--НИХУЯ НЕ РАБОТАЕТ
     *     String query = "call fn_nve_getSumm(20,7)";
     *     //--сначала должен быть создан объект Statement (stmt)
     *     try (Statement stmt = conn.createStatement()) {
     *
     *          ResultSet rs = stmt.executeQuery(query);
     *          ResultSetMetaData rsmd = rs.getMetaData();
     *
     *          int nrColumns = rsmd.getColumnCount();
     *
     *          System.out.println(nrColumns);                        //=OUTPUT: 1
     *          System.out.println(rsmd.getColumnClassName(1));       //=OUTPUT: java.lang.Integer
     *          System.out.println(rsmd.getColumnType(1));            //=OUTPUT: 4
     *          System.out.println(rsmd.getColumnTypeName(1));        //=OUTPUT: INTEGER
     *
     *          //..Что там лежит в этом rsmd?
     *          //..блять.. там лежат МЕТАДАННЫЕ resultSet, а нужны сами данные из него!
     *
     *          //..с помощью StreamAPI и функционального стиля пишем цикл выводящий Индексы и Имена полей resultSet
     *          IntStream.range(1, nrColumns).forEach(i -> {
     *              try {
     *                      System.out.println(i +": "+ rsmd.getColumnName(1));     //-- пусто
     *                      System.out.println(i +": "+ rsmd.getColumnType(1));     //-- пусто
     *                      System.out.println(i +": "+ rsmd.isNullable(1));        //-- пусто
     *              } catch (SQLException e) {
     *                      e.printStackTrace();
     *                }
     *         });
     *         //..нихрена, короче нет в resultSet в данном случае!
     *         //..ммм.. а с хуя-ли там что-то будет если метод execute() не вызывался? А?
     *         //..вообще-то вызывался: ResultSet rs = stmt.executeQuery(query);
     *     }
     *
     *
     *      //-- ТЕСТ (пример 5)
     *      //=ВЫЗОВ СВОЕЙ ФУНКЦИИ С ПОМОЩЬЮ ФУНКЦИИ ВЫЗОВА "CALL"
     *
     *      //--создаем объекты для вызова тестовой хранимой функции
     *      //--ОШИБКА.. судя по которой это синтаксис для работы с ХП а не с Функциями
     *      String preparedSql = "call fn_nve_getSumm(?,?)";
     *
     *      try (CallableStatement cstmt = conn.prepareCall(preparedSql)) {
     *          // use cstmt here
     *          cstmt.setInt(1, 10);
     *          cstmt.setInt(2, 7);
     *          cstmt.execute();
     *          int result = cstmt.getInt(1);
     *      }
     *      //=ОШИБКА:
     *      //      Invalid argument in JDBC call: Not OUT or INOUT mode: 1 for parameter: 1
     *
     *
     *      //CallableStatement cstmt = conn.prepareCall("{? = call fn_nve_getSumm(10,7)}");
     *      //cstmt.execute();
     *
     *      //cstmt.registerOutParameter(1, Types.INTEGER);       //=ОШИБКА: Invalid argument in JDBC call: parameter index out of range: 1
     *      //cstmt.registerOutParameter("out", Types.INTEGER);   //=ОШИБКА: Column not found: out
     *      //cstmt.getInt(1);                                    //=ОШИБКА: cstmt.getInt(1);
     *
     *      //int output = cstmt.getInt(1);
     *      //System.out.print(output);
     *
     *      //--
     *      cstmt.registerOutParameter(1, Types.INTEGER);       //=ERROR: Invalid argument in JDBC call: parameter index out of range: 0
     *      int value = cstmt.getInt(1);
     *
     *      System.out.println(cstmt.execute());                //=OUTPUT: true
     *      ResultSet rs = cstmt.getResultSet();                //=OUTPUT: ..пусто
     *      System.out.println(rs.next());                      //=OUTPUT: true
     *      System.out.println(rs.getCursorName());             //=OUTPUT: null
     *
     *     //---МУСОР -- КОНЕЦ
     *
     *
     *
     *     //------------------------------------------------------------------------------------------------------------
     *     //--пример#3 -- НЕ_РАБОТАЕТ -- обращение к результсету до того как был создан объект stmt
     *     //------------------------------------------------------------------------------------------------------------
     *     // =ОБРАБОТКА С 1-СТРОЧНОГО РЕЗУЛЬТАТА SELECT-ЗАПРОСА -- из [4]
     *     Statement stmt = conn.createStatement();
     *     ResultSet rs = stmt.executeQuery("select id, userid, fullname_alias, position_rus from TIMESHEET where id = 1");
     *
     *     while(rs.next()) {
     *          int recordId = rs.getInt(1);
     *          System.out.println(recordId);
     *     }
     *    //=ОШИБКА:
     *    //      invalid cursor state: identifier cursor not positioned on row in UPDATE, DELETE, SET, or GET statement: ;
     *    //      ResultSet is positioned before first row
     *    //
     *    //=РЕШЕНИЕ:
     *    // - создание stmt объекта нужно создавать внутри try блока
     *    //   после чего в теле блока создавать resultSet объект и работать с ним
     *    //   ..возможно это и есть пример позднего статического связывания..
     *    //     когда строка которая находится ниже в коде (создание Объекта rs)
     *    //     выполняется раньше предыдущей (создание Объекта stmt)
     *    //
     *
     *    //------------------------------------------------------------------------------------------------------------
     *    //=Функция недоступна
     *    //------------------------------------------------------------------------------------------------------------
     *    ResultSet rset = stmt.executeQuery("CALL SESSION_ID");
     *    //=ОШИБКА: user lacks privilege or object not found: SESSION_ID
     *    // ..код далее уже не выполняется
     *    rset.next();
     *    long sessionId = rset.getLong(1);
     *    System.out.println(sessionId);        //=OUTPUT: null
     *
     *    //--НЕ РАБОТАЕТ -- не понятно как получить значение из функции
     *    //  ЗАМЕЧАНИЕ:
     *    //  - ошибка была из-за того что я пытался обработать результат Функции как ResultSet
     *    //    хотя как написано в официальной документации HSQLDB:
     *    //    функции возвращают только либо ЕДИНИЧНОЕ значение, либо ЕДИЧНИЧНУЮ таблицу
     *    //  - а у меня получилось что при вызове Функции с помощью call,
     *    //    я завернул единичное целое значение которое возвращала моя Функция
     *    //    в объект ResultSet, для чтения значений из которого нужно знать имя Поля в котором находится значение..
     *    //    но которое у меня не было установлено.. и было непонятно как считывать значение их результсета..
     *    //    ..по идентификатору тоже не получалось
     *    //  - это имя поля можно установить в алиасе при вызове Функции в Select-запросе, после чего можно
     *    //  - работающим решением является вызов Фукнции в Select-запросе (см. в основном коде выше)
     *    //
     *    Statement stmt = conn.createStatement();
     *    ResultSet rs = stmt.executeQuery("VALUES fn_nve_getSumm(2,3)");
     *    System.out.println(rs);                                          //=OUTPUT: org.hsqldb.jdbc.JDBCResultSet@4c75cab9
     *    System.out.println(rs.getType());                                //=OUTPUT: 1003     --чё это?
     *    System.out.println(rs.getRow());                                 //=OUTPUT: 0        --чё это?
     *    //System.out.println(rs.getInt(0));                              //=ERROR:  invalid cursor state: identifier cursor not positioned on row in UPDATE, DELETE, SET, or GET statement: ; ResultSet is positioned before first row
     *
     *    //--проверяем что в resultSet что-то есть
     *    if(rs.next()) {
     *      System.out.println("true");                                    //=OUTPUT: true     --значит что-то есть
     *      //System.out.println(rs.getString("@p0"));                     //=OUTPUT: Column not found: @p0
     *      //System.out.println(rs.getInt(0));                            //=OUTPUT: Column not found: 0
     *      System.out.println(rs.getInt("VALUES"));                       //=OUTPUT: Column not found: VALUES
     *
     *    } else {
     *          System.out.println("false");
     *    }
     *
     * */

}
