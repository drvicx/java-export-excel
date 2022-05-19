package com.example.jxls;

import com.example.jxls.model.TimesheetV2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;



/* *
 * =РАБОТА С ПОЛЬЗОВАТЕЛЬСКИМИ ФУНКЦИЯМИ HSQLDB -- СОЗДАНИЕ ОБЪЕКТОВ ИЗ РЕЗУЛТСЕТА
 *  - вызов Функции которая возвращает TABLE результат из Таблицы TIMESHEET и создание на лету Объектов TimesheetV2
 *  - Функция при этом возвращает ЕДИНИЧНЫЙ результат типа TABLE содержащий НЕСКОЛЬКО значений
 *  - таблица TIMESHEET и Класс TimesheetV2 не идентичны по полям
 *
* */
public class TestHSQLDBv005 {

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


            //--РАБОТАЕТ
            // =CALL ВЫЗОВ ФУНКЦИИ БЕЗ ПАРАМЕТРОВ КОТОРАЯ ВОЗВРАЩАЕТ РЕЗУЛЬТАТ ТИПА "TABLE" С 1+ ЗАПИСЯМИ
            //  и создание списка Объектов типа TimesheetV2

            //..формируем строку запроса с вызовом функции без параметров
            String preparedSql = "{call fn_getUsersTimesheet()}";

            //..создаем блок попытки создания Объекта содержащего вызов Хранимой Функции..
            try (CallableStatement cstmt = conn.prepareCall(preparedSql)) {
                //..выполняем statement (cstmt)
                cstmt.execute();
                //..получаем результсет
                ResultSet rs = cstmt.getResultSet();

                //..создаем список для хранения объектов TimesheetV2
                List<TimesheetV2> timedatas = new ArrayList<>();

                //..в цикле обхода результсета создаем Объекты, заполняем некоторые поля и помещаем Объекты в Список
                // *этот цикл ПРЕДВАРИТЕЛЬНО смещает курсор на +1, т.о не нужно выполнять rs.next() перед ним
                //  если вообще не делать rs.next() в начале1, курсор будет показывать в пустоту и поля будут НЕ доступны
                while (rs.next()) {
                    //..создаем Объект с null полями и начинаем их инициализировать значениями из результсета приводя к типам полей класса
                    TimesheetV2 td = new TimesheetV2();
                    td.setId(Long.valueOf(rs.getString("ID")));
                    td.setUserId(Long.valueOf(rs.getString("USERID")));
                    td.setD1h(Integer.valueOf(rs.getString("D1H")));
                    td.setD1p(rs.getString("D1P"));

                    //(!)таких полей нет в классе TimesheetV2.. это я спутал с таблицей TIMESHEET в БД, Класс для которой еще не описан
                    //td.setFullnameAlias(rs.getString("FULLNAME_ALIAS"));
                    //td.setPersonalNumber(Long.valueOf(rs.getString("PERSONAL_NUMBER")));

                    //..добавляем Объект с заполненными полями в список
                    timedatas.add(td);
                }

                //--теперь пытаемся вывести значения из полей 1 Объекта из списка
                //System.out.println(
                //        timedatas.get(0).getId() +" "+  timedatas.get(0).getUserId() +" "+
                //        timedatas.get(0).getD1h() +" "+ timedatas.get(0).getD1p()
                //);

                //..выводим данные из всех объектов списка
                //while (timedatas.next()) {  .. //(!) Метода next() нет у списка, т.к это не итерируемая структура

                //..используем foreach цикл для обхода списка - лишние скобки не нужны
                for (TimesheetV2 td : timedatas)
                    System.out.println(td.getId() +" "+ td.getUserId() +" "+ td.getD1h() +" "+ td.getD1p());

            }
            //(!)это другая область видимости за пределами try блока и Объект списка тут не доступен
            //System.out.println(timedatas...);
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

    //=OUTPUT:
    /* *
     *      1	1	8	Я
     *      2   2   8   Р
     *      3   3   8   Я
     *
     * */

    //=ИСТОЧНИКИ:
    /* *
     *  2.
     *      Introduction to JDBC | Baeldung
     *      https://www.baeldung.com/java-jdbc
     *      2021.12.05
     *      - работа с БД через JDBC
     *  1.
     *      hsqldb system functions how to use
     *      https://hsqldb.org/doc/guide/sqlroutines-chapt.html
     *      - Routine Definition, Table Variables, Return Statement
     *      - SQL-invoked routines are functions and procedures called from SQL.
     *        HyperSQL 2.7 supports routines conforming to two parts of the SQL Standard.
     *        1. Routines written in the SQL language are supported in conformance to SQL/PSM (Persistent Stored Modules) specification.
     *        2. Routines written in Java are supported in broad conformance to SQL/JRT specification.
     *      - In addition, HyperSQL's previous non-standard support for calling Java routines without prior method definition
     *        is retained and enhanced in the latest version by extending the SQL/JRT specification.
     *      - HyperSQL also supports user-defined aggregate functions written in the SQL language or Java.
     *        This feature is an extension to the SQL Standard.
     *      - SQL-invoked routines are schema-level objects.
     *        Naming and referencing follows conventions common to all schema objects.
     *        The same routine name can be defined in two different schemas and used with schema-qualified references.
     *     >>>A routine is either a procedure or a function<<<
     *        ..
     *
     * */


    //=ОШИБКИ:
    /* *
     *
     *
     * */

}
