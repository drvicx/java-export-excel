package com.example.jxls;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;



/* *
 * =РАБОТА С ПОЛЬЗОВАТЕЛЬСКОЙ ХРАНИМЫМОЙ ПРОЦЕДУРОЙ HSQLDB БЕЗ ПАРАМЕТРОВ -- sp_getTestDataV2()
 *  - вызов Процедуры которая возвращает результсет выборки из таблиц [USER] и [TIMEDATA];
 *  - есть недоработка связанная с не корректным выводом значений в поле ID, но это не принципиально для текущей цели;
 *
* */
public class TestHSQLDBv007 {

    //=МЕТОД ВЫЗОВА ХП БЕЗ ПАРАМЕТРОВ -- sp_getTestDataV2()
    public static void getTestDataV2() {

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
            //  содержащий результат выборки из таблиц [USER] и [TIMEDATA]

            //..формируем строку запроса с вызовом функции без параметров
            String preparedSql = "{call sp_getTestDataV2()}";

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
                            rs.getString("DATE_"),          //--имя поля изменено, т.к "date" - зарезервированное слово;
                            rs.getString("DAYOFMONTH"),
                            rs.getString("HOUR_"),          //--имя поля изменено, т.к "hour" - зарезервированное слово;
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
        //-вызываем метод получения и вывода данных в консоль;
        getTestDataV2();
    }

    //=OUTPUT:
    /* *
     *      ID  USERID PNUMBER      FULLNAME_ALIAS    POSITION_RUS            DATE   DAYOFMONTH   HOUR    PRESENCE                             TS
     *       1       1     562           Фунт З.П.       Начальник      2020-10-01            1      8           Я     2022-05-09 21:13:22.383000
     *       1       1     562           Фунт З.П.       Начальник      2020-10-02            2      8           Я     2022-05-09 21:13:22.383000
     *       2       2     449         Бендер О.И.     Организатор      2020-10-01            1      8           Р     2022-05-09 21:13:22.383000
     *       2       2     449         Бендер О.И.     Организатор      2020-10-02            2      8           Р     2022-05-09 21:13:22.383000
     *       3       3     623      Балаганов Ш.П.     Программист      2020-10-01            1      8           Я     2022-05-09 21:13:22.383000
     *       3       3     623      Балаганов Ш.П.     Программист      2020-10-02            2      8           Я     2022-05-09 21:13:22.383000
     *
     *   (!) не корректное формирование значений в поле ID - не принципиально для текущей цели;
     *
     * */

    //=ИСТОЧНИКИ:
    /* *
     *  3.
     *      HSQL - Unexpected token: PROCEDURE in statement
     *      https://stackoverflow.com/questions/24556944/hsql-unexpected-token-procedure-in-statement
     *      2014.07.07
     *      - есть пример создания ХП которая возвращает результсет из созданной внутри табличной переменной;
     *  2.
     *      HSQL Procedure returning result set from declared table
     *      https://stackoverflow.com/questions/38585008/hsql-procedure-returning-result-set-from-declared-table
     *      2016.08.01
     *      - есть пример создание курсора возвращающего результсет на основе select-запроса;
     *  1.
     *      Introduction to JDBC | Baeldung
     *      https://www.baeldung.com/java-jdbc
     *      2021.12.05
     *      - основной пример работы с sql-запросами и процедурами в Java-приложении;
     *
     * */


    //=ОШИБКИ:
    /* *
     *
     *
     * */

}
