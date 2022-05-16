package com.example.jxls;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



/* *
 * =РАБОТА С ПОЛЬЗОВАТЕЛЬСКИМИ ХРАНИМЫМИ ПРОЦЕДУРАМИ HSQLDB -- sp_getTestData()
 *  - вызов Процедуры которая возвращает результсет выборки из таблицы [TEST_TABLE]
 *
* */
public class TestHSQLDBv006 {

    //=МЕТОД СБРОСА IDENTITY СЧЕТЧИКА В ТАБЛИЦЕ [TEST_DATA]
    public static void resetTestTable() {

        //--параметры подключения
        Connection conn = null;
        String db = "jdbc:hsqldb:hsql://localhost:9000/timesheet-udm";
        String user = "SA";
        String psswd = "";

        //--Try-Catch блок
        try {
            //--создаем объект подключения
            conn = DriverManager.getConnection(db, user, psswd);

            //--ВЫПОЛНЕНИЕ НЕСКОЛЬКИХ SQL ЗАПРОСОВ ДЛЯ ОЧИСТКИ ТАБЛИЦЫ [TEST_TABLE] И ЕЕ ID
            //..формируем строку запроса с вызовом функции без параметров
            Statement stmt = conn.createStatement();

            try (ResultSet rs = stmt.executeQuery("TRUNCATE TABLE test_table;")) {
                //..выполняем statement
                System.out.println("=LOG: Таблица TEST_DATA очищена..");
            } //--end: try

            try (ResultSet rs = stmt.executeQuery("ALTER TABLE test_table ALTER COLUMN ID RESTART WITH 1;");) {
                //..выполняем statement
                System.out.println("=LOG: Идентификатор ID Таблицы TEST_DATA сброшен в 1..\n");
            } //--end: try
        } //--end: try

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


    //=МЕТОД ОБНОВЛЕНИЯ ДАННЫХ В ТАБЛИЦЕ [TEST_TABLE]
    public static void updateTestData() {

        //--параметры подключения
        Connection conn = null;
        String db = "jdbc:hsqldb:hsql://localhost:9000/timesheet-udm";
        String user = "SA";
        String psswd = "";

        //--Try-Catch блок
        try {
            //--создаем объект подключения
            conn = DriverManager.getConnection(db, user, psswd);

            //--CALL ВЫЗОВ ПРОЦЕДУРЫ БЕЗ ПАРАМЕТРОВ КОТОРАЯ ОЧИЩАЕТ И ЗАПОЛНЯЕТ ТАБЛИЦУ [TEST_TABLE]
            //..формируем строку запроса с вызовом функции без параметров
            String preparedSql = "{call sp_updateTestData()}";

            //..создаем блок попытки создания Объекта содержащего вызов Хранимой Функции..
            try (CallableStatement cstmt = conn.prepareCall(preparedSql)) {
                //..выполняем statement (cstmt)
                cstmt.execute();
                //..результсет данная ХП не возвращает
            } //--end: try
        } //--end: try

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


    //=МЕТОД ПОЛУЧЕНИЯ ДАННЫХ ИЗ ТАБЛИЦЫ [TEST_TABLE] И ВЫВОДА РЕЗУЛЬТАТА В КОНСОЛЬ
    public static void getTestData() {

        //--параметры подключения
        Connection conn = null;
        String db = "jdbc:hsqldb:hsql://localhost:9000/timesheet-udm";
        String user = "SA";
        String psswd = "";

        //--Try-Catch блок
        try {
            //--создаем объект подключения
            conn = DriverManager.getConnection(db, user, psswd);

            //--CALL ВЫЗОВ ПРОЦЕДУРЫ БЕЗ ПАРАМЕТРОВ КОТОРАЯ ВОЗВРАЩАЕТ РЕКОРДСЕТ
            //  содержащий результат выборки из таблицы [TEST_TABLE]

            //..формируем строку запроса с вызовом функции без параметров
            String preparedSql = "{call sp_getTestData()}";

            //..создаем блок попытки создания Объекта содержащего вызов Хранимой Функции..
            try (CallableStatement cstmt = conn.prepareCall(preparedSql)) {
                //..выполняем statement (cstmt)
                cstmt.execute();
                //..получаем результсет
                ResultSet rs = cstmt.getResultSet();

                //..в цикле обхода результсета выводим значения полей
                // *этот цикл ПРЕДВАРИТЕЛЬНО смещает курсор на +1, т.о не нужно выполнять rs.next() перед ним
                //  если вообще не делать rs.next() в начале1, курсор будет показывать в пустоту и поля будут НЕ доступны

                //..шаблон определяющий ширину полей в табличном выводе
                String tableDataTemplate = "%4s%8s%8s%20s%16s%16s%13s%7s%12s%31s%n";
                //..выводим заголовки данных
                System.out.format(tableDataTemplate,
                        "ID",
                        "USERID",
                        "PNUMBER",
                        "FULLNAME_ALIAS",
                        "POSITION_RUS",
                        "DATE",
                        "DAYOFMONTH",
                        "HOUR",
                        "PRESENCE",
                        "TS"
                );
                //..выводим поля результсета
                while (rs.next()) {
                    //--обычный вывод
                    //System.out.println(rs.getString("ID") +" "+ rs.getString("USERID") +"..");

                    //--табличный вывод
                    System.out.format(tableDataTemplate,
                            rs.getString("ID"),
                            rs.getString("USERID"),
                            rs.getString("PNUMBER"),
                            rs.getString("FULLNAME_ALIAS"),
                            rs.getString("POSITION_RUS"),
                            rs.getString("DATE"),
                            rs.getString("DAYOFMONTH"),
                            rs.getString("HOUR"),
                            rs.getString("PRESENCE"),
                            rs.getString("TS")
                    );
                } //--end: while
            } //--end: try
        } //--end: try

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
        //-вызываем метод очистки таблицы;
        resetTestTable();
        //-вызываем метод заполнения таблицы данными;
        updateTestData();
        //-вызываем метод получения и вывода данных в консоль;
        getTestData();
    }

    //=OUTPUT:
    /* *
     *      =LOG: Таблица TEST_DATA очищена..
     *      =LOG: Идентификатор ID Таблицы TEST_DATA сброшен в 1..
     *
     *      ID  USERID PNUMBER      FULLNAME_ALIAS    POSITION_RUS            DATE   DAYOFMONTH   HOUR    PRESENCE                             TS
     *       1       1     562           Фунт З.П.       Начальник      2020-10-01            1      8           Я     2022-05-05 18:34:02.523000
     *       2       1     562           Фунт З.П.       Начальник      2020-10-02            2      8           Я     2022-05-05 18:34:02.523000
     *       3       2     449         Бендер О.И.     Организатор      2020-10-01            1      8           Р     2022-05-05 18:34:02.523000
     *       4       2     449         Бендер О.И.     Организатор      2020-10-02            2      8           Р     2022-05-05 18:34:02.523000
     *       5       3     623      Балаганов Ш.П.     Программист      2020-10-01            1      8           Я     2022-05-05 18:34:02.523000
     *       6       3     623      Балаганов Ш.П.     Программист      2020-10-02            2      8           Я     2022-05-05 18:34:02.523000
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
