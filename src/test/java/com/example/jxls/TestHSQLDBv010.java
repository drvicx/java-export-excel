package com.example.jxls;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


/* *
 * =РАБОТА С ПОЛЬЗОВАТЕЛЬСКОЙ ХРАНИМЫМОЙ ПРОЦЕДУРОЙ HSQLDB С ПАРАМЕТРАМИ -- sp_getTimesheetDataV2(year, month)
 *  - вызов Процедуры которая возвращает результсет выборки из таблиц [USER] и [TIMEDATA];
 *  - входные параметры: in_year, in_month - год и месяц выборки из таблицы [TIMEDATA] по полю "date";
 *  - процедура динамически вычисляет поля с подытогами t15h,t15p,t31h,t31p;
 *  - из-за проблем с функцией ROW_NUMBER(), поле ID (п/п) заполняется в Java-коде;
 *
* */
public class TestHSQLDBv010 {

    //=МЕТОД ВЫЗОВА ХП БЕЗ ПАРАМЕТРОВ -- sp_getTestDataV2()
    public static void getTimesheetDataV2() {

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
            String preparedSql = "{call sp_getTimesheetDataV2(?, ?)}";

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
                String tableDataTemplate = "%3s %7s %18s %17s %14s %5s %5s %5s %5s %20s %20s %20s %20s %31s %n";
                //..выводим заголовки данных
                System.out.format(tableDataTemplate,
                        "ID",
                        "USERID",
                        "FULLNAME_ALIAS",
                        "PERSONAL_NUMBER",
                        "POSITION_RUS",
                        "D1H",
                        "D1P",
                        "D31H",
                        "D31P",
                        "T15H",
                        "T15P",
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
                            //..
                            //--days 16..31
                            rs.getString("D31H"),
                            rs.getString("D31P"),
                            //--subtotals 01..15, 01..31
                            rs.getString("T15H"),
                            rs.getString("T15P"),
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
        getTimesheetDataV2();
    }

    //=OUTPUT:
    /* *
     *   ID  USERID     FULLNAME_ALIAS   PERSONAL_NUMBER   POSITION_RUS   D1H   D1P  D31H  D31P                 T15H                 T15P                 T31H                 T31P                              TS
     *    1       1          Фунт З.П.               562      Начальник     8     Я     0     в     Я 10, РП 1, НВ 1     Я 80, РП 8, НВ 8     Я 21, РП 1, НВ 1    Я 168, РП 8, НВ 8      2022-05-13 12:40:21.863000
     *    2       2        Бендер О.И.               449    Организатор     8     Р     0     Р                 Р 15                 Р 88                 Р 31                Р 176      2022-05-13 12:40:21.863000
     *    3       3     Балаганов Ш.П.               623    Программист     8     Я     0     в             Я 9, О 2           Я 72, О 16      Я 14, О 3, ОУ 5   Я 112, О 24, ОУ 40      2022-05-13 12:40:21.863000
     *    4       4   Паниковский М.С.               655    Программист     8     Я     0     в                 Я 11                 Я 88            Я 17, О 5          Я 136, О 40      2022-05-13 12:40:21.863000
     *    5       5      Синицкая З.В.               134      Секретарь     8     Р     0     Р                 Р 15                 Р 88                 Р 31                Р 176      2022-05-13 12:40:21.863000
     *    6       6      Козлевич А.К.                58       Водитель     8     Я     0     в           Я 11, РП 1           Я 88, РП 8           Я 22, РП 1          Я 176, РП 8      2022-05-13 12:40:21.863000
     *    7       7       Корейко А.И.                36      Бухгалтер     8     Я     0     в                 Я 11                 Я 88                 Я 22                Я 176      2022-05-13 12:40:21.863000
     *
     * */


    //=ИСТОЧНИКИ:
    /* *
     *  ..на основе собственных разработок v001..009
     *
     * */

    //=ОШИБКИ:
    /* *
     *
     *
     * */

}
