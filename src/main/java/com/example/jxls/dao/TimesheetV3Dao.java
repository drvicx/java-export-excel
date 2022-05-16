package com.example.jxls.dao;

import com.example.jxls.model.TimesheetV3;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;



/**
 *=DATA ACCESS OBJECT/CLASS FOR ENTITY CLASS "TimesheetV3"
 */
public class TimesheetV3Dao {

    //--Returns List of Objects of TimesheetV3 Class from HSQLDB Stored Procedure
    public List<TimesheetV3> getTimesheetDataFromHSQLDB() {

        List<TimesheetV3> timesheetListData = new ArrayList<>();

        //--БЛОК ПОДКЛЮЧЕНИЯ К БД
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
                String tableDataTemplate = "%10s %10s %7s %17s %20s %20s %20s %20s %31s %n";
                //..выводим некоторые данные в консоль
                //System.out.println(">>>DEBUG_START..");
                /*
                System.out.format(tableDataTemplate,
                        "TS_YEAR",
                        "TS_MONTH",
                        "USERID",
                        "PERSONAL_NUMBER",
                        "T15H",
                        "T15P",
                        "T31H",
                        "T31P",
                        "TS"
                );
                */
                //..выводим поля результсета Консоль или инициализируем поля Объектов
                //  *в ХП HSQLDB глючит конструкция { row_number() OVER() }
                //  поэтому порядковые номера строк пришлось реализовывать в Java-коде
                int row_number = 0;

                while (rs.next()) {
                    //--инкремент счетчика строк по полю ID результсета;
                    row_number++;

                    //--DEBUG-ВЫВОД В КОНСОЛЬ -- ИД записи не выводим
                    /*
                    System.out.format(tableDataTemplate,
                            //--Year&Month
                            rs.getString("TS_YEAR"),
                            rs.getString("TS_MONTH"),
                            //--userData
                            rs.getString("USERID"),
                            rs.getString("PERSONAL_NUMBER"),
                            //--subtotals 01..15, 01..31
                            rs.getString("T15H"),
                            rs.getString("T15P"),
                            rs.getString("T31H"),
                            rs.getString("T31P"),
                            //--timestamp
                            rs.getString("TS")
                    );
                    */

                    //--создаем объект TimesheetV3 и инициализируем поля данными результсета
                    TimesheetV3 obj = new TimesheetV3();
                    //..данные сотрудника
                    obj.setId((long) row_number);
                    obj.setUserId(rs.getInt("USERID"));
                    obj.setFullnameAlias(rs.getString("FULLNAME_ALIAS"));
                    obj.setPersonalNumber(rs.getInt("PERSONAL_NUMBER"));
                    obj.setPositionRus(rs.getString("POSITION_RUS"));
                    //..сетка дней 01..31
                    obj.setD1h(rs.getInt("D1H"));
                    obj.setD1p(rs.getString("D1P"));
                    obj.setD2h(rs.getInt("D2H"));
                    obj.setD2p(rs.getString("D2P"));
                    obj.setD3h(rs.getInt("D3H"));
                    obj.setD3p(rs.getString("D3P"));
                    obj.setD4h(rs.getInt("D4H"));
                    obj.setD4p(rs.getString("D4P"));
                    obj.setD5h(rs.getInt("D5H"));
                    obj.setD5p(rs.getString("D5P"));
                    obj.setD6h(rs.getInt("D6H"));
                    obj.setD6p(rs.getString("D6P"));
                    obj.setD7h(rs.getInt("D7H"));
                    obj.setD7p(rs.getString("D7P"));
                    obj.setD8h(rs.getInt("D8H"));
                    obj.setD8p(rs.getString("D8P"));
                    obj.setD9h(rs.getInt("D9H"));
                    obj.setD9p(rs.getString("D9P"));
                    obj.setD10h(rs.getInt("D10H"));
                    obj.setD10p(rs.getString("D10P"));
                    obj.setD11h(rs.getInt("D11H"));
                    obj.setD11p(rs.getString("D11P"));
                    obj.setD12h(rs.getInt("D12H"));
                    obj.setD12p(rs.getString("D12P"));
                    obj.setD13h(rs.getInt("D13H"));
                    obj.setD13p(rs.getString("D13P"));
                    obj.setD14h(rs.getInt("D14H"));
                    obj.setD14p(rs.getString("D14P"));
                    obj.setD15h(rs.getInt("D15H"));
                    obj.setD15p(rs.getString("D15P"));
                    obj.setD16h(rs.getInt("D16H"));
                    obj.setD16p(rs.getString("D16P"));
                    obj.setD17h(rs.getInt("D17H"));
                    obj.setD17p(rs.getString("D17P"));
                    obj.setD18h(rs.getInt("D18H"));
                    obj.setD18p(rs.getString("D18P"));
                    obj.setD19h(rs.getInt("D19H"));
                    obj.setD19p(rs.getString("D19P"));
                    obj.setD20h(rs.getInt("D20H"));
                    obj.setD20p(rs.getString("D20P"));
                    obj.setD21h(rs.getInt("D21H"));
                    obj.setD21p(rs.getString("D21P"));
                    obj.setD22h(rs.getInt("D22H"));
                    obj.setD22p(rs.getString("D22P"));
                    obj.setD23h(rs.getInt("D23H"));
                    obj.setD23p(rs.getString("D23P"));
                    obj.setD24h(rs.getInt("D24H"));
                    obj.setD24p(rs.getString("D24P"));
                    obj.setD25h(rs.getInt("D25H"));
                    obj.setD25p(rs.getString("D25P"));
                    obj.setD26h(rs.getInt("D26H"));
                    obj.setD26p(rs.getString("D26P"));
                    obj.setD27h(rs.getInt("D27H"));
                    obj.setD27p(rs.getString("D27P"));
                    obj.setD28h(rs.getInt("D28H"));
                    obj.setD28p(rs.getString("D28P"));
                    obj.setD29h(rs.getInt("D29H"));
                    obj.setD29p(rs.getString("D29P"));
                    obj.setD30h(rs.getInt("D30H"));
                    obj.setD30p(rs.getString("D30P"));
                    obj.setD31h(rs.getInt("D31H"));
                    obj.setD31p(rs.getString("D31P"));
                    //..подытоги 01..15, 01..31
                    obj.setT15h(rs.getString("T15H"));
                    obj.setT15p(rs.getString("T15P"));
                    obj.setT31h(rs.getString("T31H"));
                    obj.setT31p(rs.getString("T31P"));
                    //..год и месяц
                    obj.setTsYear(rs.getInt("TS_YEAR"));
                    obj.setTsMonth(rs.getInt("TS_MONTH"));
                    //..штамп времени извлечения данных
                    obj.setTs(rs.getTimestamp("TS"));

                    //..добавляем объект в список
                    timesheetListData.add(obj);
                } //--end: while
                //System.out.println("<<<END_DEBUG");

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

        //--ВЫВОДИМ СПИСОК ОБЪЕКТОВ
        return timesheetListData;

        /*=DEBUG_OUTPUT:
         *
         *   >>>DEBUG_START..
         *      TS_YEAR   TS_MONTH  USERID    PERSONAL_NUMBER                T15H                T15P                T31H                 T31P                              TS
         *         2020         10       1                562    Я 10, РП 1, НВ 1    Я 80, РП 8, НВ 8    Я 21, РП 1, НВ 1    Я 168, РП 8, НВ 8      2022-05-15 19:50:49.651000
         *         2020         10       2                449                Р 15                Р 88                Р 31                Р 176      2022-05-15 19:50:49.651000
         *         2020         10       3                623            Я 9, О 2          Я 72, О 16     Я 14, О 3, ОУ 5   Я 112, О 24, ОУ 40      2022-05-15 19:50:49.651000
         *         2020         10       4                655                Я 11                Я 88           Я 17, О 5          Я 136, О 40      2022-05-15 19:50:49.651000
         *         2020         10       5                134                Р 15                Р 88                Р 31                Р 176      2022-05-15 19:50:49.651000
         *         2020         10       6                 58          Я 11, РП 1          Я 88, РП 8          Я 22, РП 1          Я 176, РП 8      2022-05-15 19:50:49.651000
         *         2020         10       7                 36                Я 11                Я 88                Я 22                Я 176      2022-05-15 19:50:49.651000
         *   <<<END_DEBUG
         *
         * */

    }


    //--Returns List of Static/Test Objects of TimesheetV3 Class
    public List<TimesheetV3> getListDataTest1() {

        long now = System.currentTimeMillis();
        Timestamp sqlTimestamp = new Timestamp(now);

        List<TimesheetV3> timesheetListData = new ArrayList<>();

        //--Single TimesheetV3 Object/Record----------------------------------------------------------------------------
        TimesheetV3 obj1 = new TimesheetV3();

        obj1.setId(1L);
        obj1.setUserId(1);
        obj1.setFullnameAlias("Фунт З.П.");
        obj1.setPersonalNumber(562);
        obj1.setPositionRus("Начальник");
        // day01
        obj1.setD1h(8);
        obj1.setD1p("Я");
        // day02
        obj1.setD2h(8);
        obj1.setD2p("Я");
        // day03
        obj1.setD3h(8);
        obj1.setD3p("РП");
        // day04
        obj1.setD4h(0);
        obj1.setD4p("в");
        // day05
        obj1.setD5h(8);
        obj1.setD5p("Я");
        // day06
        obj1.setD6h(8);
        obj1.setD6p("Я");
        // day07
        obj1.setD7h(8);
        obj1.setD7p("Я");
        // day08
        obj1.setD8h(8);
        obj1.setD8p("Я");
        // day09
        obj1.setD9h(8);
        obj1.setD9p("Я");
        // day10
        obj1.setD10h(0);
        obj1.setD10p("в");
        // day11
        obj1.setD11h(0);
        obj1.setD11p("в");
        // day12
        obj1.setD12h(8);
        obj1.setD12p("НВ");
        // day13
        obj1.setD13h(8);
        obj1.setD13p("Я");
        // day14
        obj1.setD14h(8);
        obj1.setD14p("Я");
        // day15
        obj1.setD15h(8);
        obj1.setD15p("Я");
        // day16
        obj1.setD16h(8);
        obj1.setD16p("Я");
        // day17
        obj1.setD17h(0);
        obj1.setD17p("в");
        // day18
        obj1.setD18h(0);
        obj1.setD18p("в");
        // day19
        obj1.setD19h(8);
        obj1.setD19p("Я");
        // day20
        obj1.setD20h(8);
        obj1.setD20p("Я");
        // day21
        obj1.setD21h(8);
        obj1.setD21p("Я");
        // day22
        obj1.setD22h(8);
        obj1.setD22p("Я");
        // day23
        obj1.setD23h(8);
        obj1.setD23p("Я");
        // day24
        obj1.setD24h(0);
        obj1.setD24p("в");
        // day25
        obj1.setD25h(0);
        obj1.setD25p("в");
        // day26
        obj1.setD26h(8);
        obj1.setD26p("Я");
        // day27
        obj1.setD27h(8);
        obj1.setD27p("Я");
        // day28
        obj1.setD28h(8);
        obj1.setD28p("Я");
        // day29
        obj1.setD29h(8);
        obj1.setD29p("Я");
        // day30
        obj1.setD30h(8);
        obj1.setD30p("Я");
        // day31
        obj1.setD31h(0);
        obj1.setD31p("в");

        // subtotals 01..15, 01..31
        obj1.setT15h("Я 10, РП 1, НВ 1");
        obj1.setT15p("Я 80, РП 8, НВ 8");
        obj1.setT31h("Я 21, РП 1, НВ 1");
        obj1.setT31p("Я 168, РП 8, НВ 8");

        // Year & Month of Timesheet Record
        obj1.setTsYear(2020);
        obj1.setTsMonth(10);
        // timestamp
        obj1.setTs(sqlTimestamp);


        //--Single TimesheetV3 Object/Record----------------------------------------------------------------------------
        TimesheetV3 obj2 = new TimesheetV3();

        obj2.setId(2L);
        obj2.setUserId(3);
        obj2.setFullnameAlias("Балаганов Ш.П.");
        obj2.setPersonalNumber(623);
        obj2.setPositionRus("Программист");
        // day01
        obj2.setD1h(8);
        obj2.setD1p("Я");
        // day02
        obj2.setD2h(8);
        obj2.setD2p("Я");
        // day03
        obj2.setD3h(0);
        obj2.setD3p("в");
        // day04
        obj2.setD4h(0);
        obj2.setD4p("в");
        // day05
        obj2.setD5h(8);
        obj2.setD5p("Я");
        // day06
        obj2.setD6h(8);
        obj2.setD6p("Я");
        // day07
        obj2.setD7h(8);
        obj2.setD7p("Я");
        // day08
        obj2.setD8h(8);
        obj2.setD8p("Я");
        // day09
        obj2.setD9h(8);
        obj2.setD9p("Я");
        // day10
        obj2.setD10h(0);
        obj2.setD10p("в");
        // day11
        obj2.setD11h(0);
        obj2.setD11p("в");
        // day12
        obj2.setD12h(8);
        obj2.setD12p("Я");
        // day13
        obj2.setD13h(8);
        obj2.setD13p("Я");
        // day14
        obj2.setD14h(8);
        obj2.setD14p("О");
        // day15
        obj2.setD15h(8);
        obj2.setD15p("О");
        // day16
        obj2.setD16h(8);
        obj2.setD16p("О");
        // day17
        obj2.setD17h(0);
        obj2.setD17p("в");
        // day18
        obj2.setD18h(0);
        obj2.setD18p("в");
        // day19
        obj2.setD19h(8);
        obj2.setD19p("Я");
        // day20
        obj2.setD20h(8);
        obj2.setD20p("Я");
        // day21
        obj2.setD21h(8);
        obj2.setD21p("Я");
        // day22
        obj2.setD22h(8);
        obj2.setD22p("Я");
        // day23
        obj2.setD23h(8);
        obj2.setD23p("Я");
        // day24
        obj2.setD24h(0);
        obj2.setD24p("в");
        // day25
        obj2.setD25h(0);
        obj2.setD25p("в");
        // day26
        obj2.setD26h(8);
        obj2.setD26p("ОУ");
        // day27
        obj2.setD27h(8);
        obj2.setD27p("ОУ");
        // day28
        obj2.setD28h(8);
        obj2.setD28p("ОУ");
        // day29
        obj2.setD29h(8);
        obj2.setD29p("ОУ");
        // day30
        obj2.setD30h(8);
        obj2.setD30p("ОУ");
        // day31
        obj2.setD31h(0);
        obj2.setD31p("в");

        // subtotals 01..15, 01..31
        obj2.setT15h("Я 9, О 2");
        obj2.setT15p("Я 72, О 16");
        obj2.setT31h("Я 14, О 3, ОУ 5");
        obj2.setT31p("Я 112, О 24, ОУ 40");

        // Year & Month of Timesheet Record
        obj2.setTsYear(2020);
        obj2.setTsMonth(10);
        // timestamp
        obj2.setTs(sqlTimestamp);


        //--Single TimesheetV3 Object/Record----------------------------------------------------------------------------
        TimesheetV3 obj3 = new TimesheetV3();

        obj3.setId(3L);
        obj3.setUserId(6);
        obj3.setFullnameAlias("Козлевич А.К.");
        obj3.setPersonalNumber(58);
        obj3.setPositionRus("Водитель");
        // day01
        obj3.setD1h(8);
        obj3.setD1p("Я");
        // day02
        obj3.setD2h(8);
        obj3.setD2p("Я");
        // day03
        obj3.setD3h(8);
        obj3.setD3p("РП");
        // day04
        obj3.setD4h(0);
        obj3.setD4p("в");
        // day05
        obj3.setD5h(8);
        obj3.setD5p("Я");
        // day06
        obj3.setD6h(8);
        obj3.setD6p("Я");
        // day07
        obj3.setD7h(8);
        obj3.setD7p("Я");
        // day08
        obj3.setD8h(8);
        obj3.setD8p("Я");
        // day09
        obj3.setD9h(8);
        obj3.setD9p("Я");
        // day10
        obj3.setD10h(0);
        obj3.setD10p("в");
        // day11
        obj3.setD11h(0);
        obj3.setD11p("в");
        // day12
        obj3.setD12h(8);
        obj3.setD12p("Я");
        // day13
        obj3.setD13h(8);
        obj3.setD13p("Я");
        // day14
        obj3.setD14h(8);
        obj3.setD14p("Я");
        // day15
        obj3.setD15h(8);
        obj3.setD15p("Я");
        // day16
        obj3.setD16h(8);
        obj3.setD16p("Я");
        // day17
        obj3.setD17h(0);
        obj3.setD17p("в");
        // day18
        obj3.setD18h(0);
        obj3.setD18p("в");
        // day19
        obj3.setD19h(8);
        obj3.setD19p("Я");
        // day20
        obj3.setD20h(8);
        obj3.setD20p("Я");
        // day21
        obj3.setD21h(8);
        obj3.setD21p("Я");
        // day22
        obj3.setD22h(8);
        obj3.setD22p("Я");
        // day23
        obj3.setD23h(8);
        obj3.setD23p("Я");
        // day24
        obj3.setD24h(0);
        obj3.setD24p("в");
        // day25
        obj3.setD25h(0);
        obj3.setD25p("в");
        // day26
        obj3.setD26h(8);
        obj3.setD26p("Я");
        // day27
        obj3.setD27h(8);
        obj3.setD27p("Я");
        // day28
        obj3.setD28h(8);
        obj3.setD28p("Я");
        // day29
        obj3.setD29h(8);
        obj3.setD29p("Я");
        // day30
        obj3.setD30h(8);
        obj3.setD30p("Я");
        // day31
        obj3.setD31h(0);
        obj3.setD31p("в");

        // subtotals 01..15, 01..31
        obj3.setT15h("Я 11, РП 1");
        obj3.setT15p("Я 88, РП 8");
        obj3.setT31h("Я 22, РП 1");
        obj3.setT31p("Я 176, РП 8");

        // Year & Month of Timesheet Record
        obj3.setTsYear(2020);
        obj3.setTsMonth(10);
        // timestamp
        obj3.setTs(sqlTimestamp);


        //--Add Objects to Array;---------------------------------------------------------------------------------------
        timesheetListData.add(obj1);
        timesheetListData.add(obj2);
        timesheetListData.add(obj3);

        //--Return Array List of TimesheetV3 Objects
        return timesheetListData;

    }
}
