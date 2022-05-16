package com.example.jxls;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


/* *
 * =РАБОТА С ПОЛЬЗОВАТЕЛЬСКОЙ ХРАНИМЫМОЙ ПРОЦЕДУРОЙ HSQLDB С ПАРАМЕТРАМИ -- sp_getTimesheetDataV1(year, month)
 *  - вызов Процедуры которая возвращает результсет выборки из таблиц [USER] и [TIMEDATA];
 *  - входные параметры: in_year, in_month - год и месяц выборки из таблицы [TIMEDATA] по полю "date";
 *  - есть недоработка связанная с не корректным выводом значений в поле ID, но это не принципиально для текущей цели;
 *
* */
public class TestHSQLDBv009 {

    //=МЕТОД ВЫЗОВА ХП БЕЗ ПАРАМЕТРОВ -- sp_getTestDataV2()
    public static void getTimesheetDataV1() {

        //--параметры подключения
        Connection conn = null;
        String db = "jdbc:hsqldb:hsql://localhost:9000/timesheet-udm";
        String user = "SA";
        String psswd = "";

        //--параметры для ХП - Год и Месяц выборки из таблицы [TIMEDATA]
        int in_year = 2020;
        int in_month = 10;

        //--Try-Catch блок
        try {
            //--создаем объект подключения
            conn = DriverManager.getConnection(db, user, psswd);

            //--CALL ВЫЗОВ ПРОЦЕДУРЫ БЕЗ ПАРАМЕТРОВ КОТОРАЯ ВОЗВРАЩАЕТ РЕКОРДСЕТ
            //  содержащий результат выборки из таблиц [USER] и [TIMEDATA]

            //..формируем строку запроса с вызовом функции без параметров
            String preparedSql = "{call sp_getTimesheetDataV1(?, ?)}";

            //..создаем блок попытки создания Объекта содержащего вызов Хранимой Функции..
            try (CallableStatement cstmt = conn.prepareCall(preparedSql)) {
                //..устанавливаем входные параметры для ХП
                cstmt.setInt(1, in_year);
                cstmt.setInt(2, in_month);
                //..выполняем statement (cstmt)
                cstmt.execute();
                //..получаем результсет
                ResultSet rs = cstmt.getResultSet();

                //..в цикле обхода результсета выводим значения полей
                // *этот цикл ПРЕДВАРИТЕЛЬНО смещает курсор на +1, т.о не нужно выполнять rs.next() перед ним
                //  если вообще не делать rs.next() в начале1, курсор будет показывать в пустоту и поля будут НЕ доступны

                //..шаблон определяющий ширину полей в табличном выводе
                String tableDataTemplate = "%3s %7s %18s %17s %14s %5s %5s %12s %12s %5s %5s %12s %12s %31s %n";
                //..выводим заголовки данных
                System.out.format(tableDataTemplate,
                        "ID",
                        "USERID",
                        "FULLNAME_ALIAS",
                        "PERSONAL_NUMBER",
                        "POSITION_RUS",
                        "D1H",
                        "D1P",
                        "T15H",
                        "T15P",
                        "D31H",
                        "D31P",
                        "T31H",
                        "T31P",
                        "TS"
                );
                //..выводим поля результсета
                //  *в ХП HSQLDB глючит конструкция { row_number() OVER() }
                //  поэтому порядковые номера строк пришлось реализовывать в Java-коде
                int row_number = 0;

                while (rs.next()) {
                    //--инкремент счетчика строк по полю ID результсета;
                    row_number++;

                    //--табличный вывод
                    System.out.format(tableDataTemplate,
                            //rs.getString("ID"),
                            row_number,
                            rs.getString("USERID"),
                            rs.getString("FULLNAME_ALIAS"),
                            rs.getString("PERSONAL_NUMBER"),
                            rs.getString("POSITION_RUS"),
                            //--days 01..15
                            rs.getString("D1H"),
                            rs.getString("D1P"),
                            //--subtotal 01..15
                            rs.getString("T15H"),
                            rs.getString("T15P"),
                            //--days 16..31
                            rs.getString("D31H"),
                            rs.getString("D31P"),
                            //
                            rs.getString("T31H"),
                            rs.getString("T31P"),
                            //--timestamp
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
        getTimesheetDataV1();
    }

    //=OUTPUT:
    /* *
     *      ID  USERID     FULLNAME_ALIAS   PERSONAL_NUMBER   POSITION_RUS   D1H   D1P         T15H         T15P  D31H  D31P         T31H         T31P                              TS
     *       1       1          Фунт З.П.               562      Начальник     8     Я    test_t15h    test_t15p     0     в    test_t31h    test_t31p      2022-05-10 20:04:37.209000
     *       2       2        Бендер О.И.               449    Организатор     8     Р    test_t15h    test_t15p     0     Р    test_t31h    test_t31p      2022-05-10 20:04:37.209000
     *       3       3     Балаганов Ш.П.               623    Программист     8     Я    test_t15h    test_t15p     0     в    test_t31h    test_t31p      2022-05-10 20:04:37.209000
     *       4       4   Паниковский М.С.               655    Программист     8     Я    test_t15h    test_t15p     0     в    test_t31h    test_t31p      2022-05-10 20:04:37.209000
     *       5       5      Синицкая З.В.               134      Секретарь     8     Р    test_t15h    test_t15p     0     Р    test_t31h    test_t31p      2022-05-10 20:04:37.209000
     *       6       6      Козлевич А.К.                58       Водитель     8     Я    test_t15h    test_t15p     0     в    test_t31h    test_t31p      2022-05-10 20:04:37.209000
     *       7       7       Корейко А.И.                36      Бухгалтер     8     Я    test_t15h    test_t15p     0     в    test_t31h    test_t31p      2022-05-10 20:04:37.209000
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
