package com.example.jxls;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


/* *
 * =РАБОТА С ПОЛЬЗОВАТЕЛЬСКИМИ ФУНКЦИЯМИ HSQLDB -- ВЫВОД РЕЗУЛТСЕТА
 *  - вызов Функции с помощью call без передачи параметров
 *  - Функция при этом возвращает ЕДИНИЧНЫЙ результат типа TABLE содержащий ОДНО или НЕСКОЛЬКО значений
 *
* */
public class TestHSQLDBv004 {

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

            //..формируем строку запроса с вызовом функции без параметров
            String preparedSql = "{call fn_getUsersTimesheet()}";

            //..создаем блок попытки создания Объекта содержащего вызов Хранимой Функции..
            try (CallableStatement cstmt = conn.prepareCall(preparedSql)) {
                //..выполняем statement (cstmt)
                cstmt.execute();
                //..получаем результсет
                ResultSet rs = cstmt.getResultSet();
                //..выводим в цикле поля результсета
                System.out.println("ID\tUSERID\tFIO\t\t\tPNUM\tD1H\tD1T");
                //--этот цикл ПРЕДВАРИТЕЛЬНО смещает курсор на +1, т.о не нужно выполнять rs.next() перед ним
                //  если вообще не делать rs.next() в начале1, курсор будет показывать в пустоту и поля будут НЕ доступны
                while (rs.next()) {
                    System.out.println(
                            rs.getString("ID") +"\t"+
                            rs.getString("USERID") +"\t\t"+
                            rs.getString("FULLNAME_ALIAS") + "\t"+
                            rs.getString("PERSONAL_NUMBER") + "\t\t"+
                            rs.getString("D1H") + "\t"+
                            rs.getString("D1P")
                    );
                }
            }
            //=OUTPUT:
            //      ID	USERID	FIO			    PNUM	D1H	D1T
            //      1	1		Фунт З.П.	    562		8	Я
            //      2	2		Бендер О.И.	    449		8	Р
            //      3	3		Балаганов Ш.П.	623		8	Я



            //--РАБОТАЕТ
            // =CALL ВЫЗОВ ФУНКЦИИ С 1 ПАРАМЕТРОМ КОТОРАЯ ВОЗВРАЩАЕТ РЕЗУЛЬТАТ ТИПА "TABLE" С 1 ЗАПИСЬЮ
            /*
            //..инициализируем параметр - ИД сотрудника
            int in_userId = 2;

            //..формируем строку запроса без явного указания параметра
            String preparedSql = "{call fn_getUserTimesheetByUserId(?)}";

            //..создаем блок попытки создания Объекта содержащего вызов Хранимой Функции..
            try (CallableStatement cstmt = conn.prepareCall(preparedSql)) {
                //..инициализируем значение параметра (по его ИД) в строке подготовленного запроса (cstmt)
                cstmt.setInt(1, in_userId);
                //..выполняем statement (cstmt)
                cstmt.execute();
                //..получаем результсет
                ResultSet rs = cstmt.getResultSet();
                //..выводим в цикле поля результсета
                System.out.println("ID\tUSERID\tFIO\t\t\tPNUM\tD1H\tD1T");
                //--этот цикл ПРЕДВАРИТЕЛЬНО смещает курсор на +1, т.о не нужно выполнять rs.next() перед ним
                //  если вообще не делать rs.next() в начале1, курсор будет показывать в пустоту и поля будут НЕ доступны
                while (rs.next()) {
                    System.out.println(
                            rs.getString("ID") +"\t"+
                            rs.getString("USERID") +"\t\t"+
                            rs.getString("FULLNAME_ALIAS") + "\t"+
                            rs.getString("PERSONAL_NUMBER") + "\t\t"+
                            rs.getString("D1H") + "\t"+
                            rs.getString("D1P")
                    );
                }
            }
            */
            //=OUTPUT -- userId=1:
            //      ID	USERID	FIO			PNUM	D1H	D1T
            //      1	1		Фунт З.П.	562		8	Я

            //=OUTPUT -- userId=2:
            //      ID	USERID	FIO			PNUM	D1H	D1T
            //      2	2		Бендер О.И.	449		8	Р
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

    //=OUTPUT:
    /* *
     *      ID	USERID	FIO			    PNUM	D1H	D1T
     *      1	1		Фунт З.П.	    562		8	Я
     *      2	2		Бендер О.И.	    449		8	Р
     *      3	3		Балаганов Ш.П.	623		8	Я
     *
     * */

    //=ИСТОЧНИКИ:
    /* *
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
