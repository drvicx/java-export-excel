package com.example.jxls;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


/* *
 * =РАБОТА С ПОЛЬЗОВАТЕЛЬСКОЙ ХРАНИМЫМОЙ ПРОЦЕДУРОЙ HSQLDB С ПАРАМЕТРАМИ -- sp_getTimesheetDataV3(year, month)
 *  - вызов Процедуры которая возвращает результсет выборки из таблиц [USER] и [TIMEDATA];
 *  - входные параметры: in_year, in_month - год и месяц выборки из таблицы [TIMEDATA] по полю "date";
 *  - процедура динамически вычисляет поля с подытогами t15h,t15p,t31h,t31p;
 *  - в процедуре есть доп.поля которых нет у версии V2;
 *  - из-за проблем с функцией ROW_NUMBER(), поле ID (п/п) заполняется в Java-коде;
 *
* */
public class TestHSQLDBv011 {

    //=МЕТОД ВЫЗОВА ХП БЕЗ ПАРАМЕТРОВ -- sp_getTestDataV2()
    public static void getTimesheetDataV3() {

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
            String preparedSql = "{call sp_getTimesheetDataV3(?, ?)}";

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
                String tableDataTemplate = "%3s %7s %18s %17s %14s " +
                        "%5s %5s %5s %5s %5s %5s %5s %5s %5s %5s %5s %5s %5s %5s %5s %5s %5s %5s %5s %5s %5s %5s %5s %5s %5s %5s %5s %5s %5s %5s" +
                        "%5s %5s %5s %5s %5s %5s %5s %5s %5s %5s %5s %5s %5s %5s %5s %5s %5s %5s %5s %5s %5s %5s %5s %5s %5s %5s %5s %5s %5s %5s %5s %5s" +
                        "%20s %20s %20s %20s %10s %10s %28s %n";
                //..выводим заголовки данных
                System.out.format(tableDataTemplate,
                        "ID",
                        "USERID",
                        "FULLNAME_ALIAS",
                        "PERSONAL_NUMBER",
                        "POSITION_RUS",
                        "D1H",  "D1P",
                        "D2H",  "D2P",
                        "D3H",  "D3P",
                        "D4H",  "D4P",
                        "D5H",  "D5P",
                        "D6H",  "D6P",
                        "D7H",  "D7P",
                        "D8H",  "D8P",
                        "D9H",  "D9P",
                        "D10H", "D10P",
                        "D11H", "D11P",
                        "D12H", "D12P",
                        "D13H", "D13P",
                        "D14H", "D14P",
                        "D15H", "D15P",
                        "D16H", "D16P",
                        "D17H", "D17P",
                        "D18H", "D18P",
                        "D19H", "D19P",
                        "D20H", "D20P",
                        "D21H", "D21P",
                        "D22H", "D22P",
                        "D23H", "D23P",
                        "D24H", "D24P",
                        "D25H", "D25P",
                        "D26H", "D26P",
                        "D27H", "D27P",
                        "D28H", "D28P",
                        "D29H", "D29P",
                        "D30H", "D30P",
                        "D31H", "D31P",
                        "T15H", "T15P",
                        "T31H", "T31P",
                        "TS_YEAR",
                        "TS_MONTH",
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
                            //..row id
                            row_number,
                            //..user data
                            rs.getInt("USERID"),
                            rs.getString("FULLNAME_ALIAS"),
                            rs.getInt("PERSONAL_NUMBER"),
                            rs.getString("POSITION_RUS"),
                            //..days data 01..31
                            rs.getInt("D1H"),
                            rs.getString("D1P"),
                            rs.getInt("D2H"),
                            rs.getString("D2P"),
                            rs.getInt("D3H"),
                            rs.getString("D3P"),
                            rs.getInt("D4H"),
                            rs.getString("D4P"),
                            rs.getInt("D5H"),
                            rs.getString("D5P"),
                            rs.getInt("D6H"),
                            rs.getString("D6P"),
                            rs.getInt("D7H"),
                            rs.getString("D7P"),
                            rs.getInt("D8H"),
                            rs.getString("D8P"),
                            rs.getInt("D9H"),
                            rs.getString("D9P"),
                            rs.getInt("D10H"),
                            rs.getString("D10P"),
                            rs.getInt("D11H"),
                            rs.getString("D11P"),
                            rs.getInt("D12H"),
                            rs.getString("D12P"),
                            rs.getInt("D13H"),
                            rs.getString("D13P"),
                            rs.getInt("D14H"),
                            rs.getString("D14P"),
                            rs.getInt("D15H"),
                            rs.getString("D15P"),
                            rs.getInt("D16H"),
                            rs.getString("D16P"),
                            rs.getInt("D17H"),
                            rs.getString("D17P"),
                            rs.getInt("D18H"),
                            rs.getString("D18P"),
                            rs.getInt("D19H"),
                            rs.getString("D19P"),
                            rs.getInt("D20H"),
                            rs.getString("D20P"),
                            rs.getInt("D21H"),
                            rs.getString("D21P"),
                            rs.getInt("D22H"),
                            rs.getString("D22P"),
                            rs.getInt("D23H"),
                            rs.getString("D23P"),
                            rs.getInt("D24H"),
                            rs.getString("D24P"),
                            rs.getInt("D25H"),
                            rs.getString("D25P"),
                            rs.getInt("D26H"),
                            rs.getString("D26P"),
                            rs.getInt("D27H"),
                            rs.getString("D27P"),
                            rs.getInt("D28H"),
                            rs.getString("D28P"),
                            rs.getInt("D29H"),
                            rs.getString("D29P"),
                            rs.getInt("D30H"),
                            rs.getString("D30P"),
                            rs.getInt("D31H"),
                            rs.getString("D31P"),
                            //..подытоги 01..15, 01..31
                            rs.getString("T15H"),
                            rs.getString("T15P"),
                            rs.getString("T31H"),
                            rs.getString("T31P"),
                            //..год и месяц -- extra fields
                            rs.getInt("TS_YEAR"),
                            rs.getInt("TS_MONTH"),
                            //..штамп времени извлечения данных
                            rs.getTimestamp("TS")
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
        getTimesheetDataV3();
    }

    //=OUTPUT:
    /* *
     *  ID  USERID     FULLNAME_ALIAS   PERSONAL_NUMBER   POSITION_RUS   D1H   D1P   D2H   D2P   D3H   D3P   D4H   D4P   D5H   D5P   D6H   D6P   D7H   D7P   D8H   D8P   D9H   D9P  D10H  D10P  D11H  D11P  D12H  D12P  D13H  D13P  D14H  D14P  D15H  D15P D16H  D16P  D17H  D17P  D18H  D18P  D19H  D19P  D20H  D20P  D21H  D21P  D22H  D22P  D23H  D23P  D24H  D24P  D25H  D25P  D26H  D26P  D27H  D27P  D28H  D28P  D29H  D29P  D30H  D30P  D31H  D31P                T15H                 T15P                 T31H                 T31P    TS_YEAR   TS_MONTH                           TS
     *   1       1          Фунт З.П.               562      Начальник     8     Я     8     Я     8    РП     0     в     8     Я     8     Я     8     Я     8     Я     8     Я     0     в     0     в     8    НВ     8     Я     8     Я     8     Я    8     Я     0     в     0     в     8     Я     8     Я     8     Я     8     Я     8     Я     0     в     0     в     8     Я     8     Я     8     Я     8     Я     8     Я     0     в    Я 10, РП 1, НВ 1     Я 80, РП 8, НВ 8     Я 21, РП 1, НВ 1    Я 168, РП 8, НВ 8       2020         10      2022-05-16 12:03:00.847
     *   2       2        Бендер О.И.               449    Организатор     8     Р     8     Р     0     Р     0     Р     8     Р     8     Р     8     Р     8     Р     8     Р     0     Р     0     Р     8     Р     8     Р     8     Р     8     Р    8     Р     0     Р     0     Р     8     Р     8     Р     8     Р     8     Р     8     Р     0     Р     0     Р     8     Р     8     Р     8     Р     8     Р     8     Р     0     Р                Р 15                 Р 88                 Р 31                Р 176       2020         10      2022-05-16 12:03:00.847
     *   3       3     Балаганов Ш.П.               623    Программист     8     Я     8     Я     0     в     0     в     8     Я     8     Я     8     Я     8     Я     8     Я     0     в     0     в     8     Я     8     Я     8     О     8     О    8     О     0     в     0     в     8     Я     8     Я     8     Я     8     Я     8     Я     0     в     0     в     8    ОУ     8    ОУ     8    ОУ     8    ОУ     8    ОУ     0     в            Я 9, О 2           Я 72, О 16      Я 14, О 3, ОУ 5   Я 112, О 24, ОУ 40       2020         10      2022-05-16 12:03:00.847
     *   4       4   Паниковский М.С.               655    Программист     8     Я     8     Я     0     в     0     в     8     Я     8     Я     8     Я     8     Я     8     Я     0     в     0     в     8     Я     8     Я     8     Я     8     Я    8     Я     0     в     0     в     8     О     8     О     8     О     8     О     8     О     0     в     0     в     8     Я     8     Я     8     Я     8     Я     8     Я     0     в                Я 11                 Я 88            Я 17, О 5          Я 136, О 40       2020         10      2022-05-16 12:03:00.847
     *   5       5      Синицкая З.В.               134      Секретарь     8     Р     8     Р     0     Р     0     Р     8     Р     8     Р     8     Р     8     Р     8     Р     0     Р     0     Р     8     Р     8     Р     8     Р     8     Р    8     Р     0     Р     0     Р     8     Р     8     Р     8     Р     8     Р     8     Р     0     Р     0     Р     8     Р     8     Р     8     Р     8     Р     8     Р     0     Р                Р 15                 Р 88                 Р 31                Р 176       2020         10      2022-05-16 12:03:00.847
     *   6       6      Козлевич А.К.                58       Водитель     8     Я     8     Я     8    РП     0     в     8     Я     8     Я     8     Я     8     Я     8     Я     0     в     0     в     8     Я     8     Я     8     Я     8     Я    8     Я     0     в     0     в     8     Я     8     Я     8     Я     8     Я     8     Я     0     в     0     в     8     Я     8     Я     8     Я     8     Я     8     Я     0     в          Я 11, РП 1           Я 88, РП 8           Я 22, РП 1          Я 176, РП 8       2020         10      2022-05-16 12:03:00.847
     *   7       7       Корейко А.И.                36      Бухгалтер     8     Я     8     Я     0     в     0     в     8     Я     8     Я     8     Я     8     Я     8     Я     0     в     0     в     8     Я     8     Я     8     Я     8     Я    8     Я     0     в     0     в     8     Я     8     Я     8     Я     8     Я     8     Я     0     в     0     в     8     Я     8     Я     8     Я     8     Я     8     Я     0     в                Я 11                 Я 88                 Я 22                Я 176       2020         10      2022-05-16 12:03:00.847
     *
     * */


    //=ИСТОЧНИКИ:
    /* *
     *  ..на основе собственных разработок v001..010
     *
     * */

    //=ОШИБКИ:
    /* *
     *
     *
     * */

}
