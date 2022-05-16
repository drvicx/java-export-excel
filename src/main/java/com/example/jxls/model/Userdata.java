package com.example.jxls.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.ArrayList;


/**
 *=КЛАСС КОТОРЫЙ СОБИРАЕТ ОБЪЕКТЫ ТИПА User и Timedata В ОДИН -- версия-1
 *
*/
@Getter
@Setter
public class Userdata {

    private final User u;
    //private final List<Timedata> td_raw;
    //private final List<UserDayData> td;
    private final UserMonthData t;

    //--CONSTRUCTOR: version-1
    /*
   //public Userdata(User user, List<Timedata> timedata, List<UserDayData> monthdata) {
    public Userdata(User user, List<Timedata> timedata) {
        this.u = user;
        this.td_raw = timedata;
        this.td = getMonthdata();
    }
    */

    //--CONSTRUCTOR: version-2
    public Userdata(User user, List<Timedata> timedata) {
        this.u = user;
        this.t = getUserMonthData(timedata);

        //--неудачные версии
        //this.t = getUserMonthData();          //--в этом случае поле оказывается пустым
        //this.td_raw = timedata;
        //this.td = getMonthdata();
    }


    //--ПРОБЛЕМА при выводе в Excel-шаблон: нет доступа к полям комлексных объектов, например: timedata.userdaydata.hours
    //        14:52:13.852 [main] DEBUG org.apache.commons.jexl3.JexlEngine - org.jxls.expression.JexlExpressionEvaluator.evaluate@1:4![3,6]: 'd.t.d1.h' unsolvable property 'd1'
    //        14:52:13.853 [main] DEBUG org.apache.commons.jexl3.JexlEngine - org.jxls.expression.JexlExpressionEvaluator.evaluate@1:4![3,5]: 'd.t.p' unsolvable property 'p'
    //        14:52:13.854 [main] DEBUG org.apache.commons.jexl3.JexlEngine - org.jxls.expression.JexlExpressionEvaluator.evaluate@1:4![3,6]: 'd.t.d2.h' unsolvable property 'd2'
    //
    private UserMonthData getUserMonthData(List<Timedata> timedata) {

        //UserDayData d1 =   new UserDayData(1, 8, "Я");

        //--цикл для обхода списка.. который нам в тесте не нужен т.к в timedata данные за 1 месяц (31 день)
        /*
        for (Timedata td : timedata) {
            //System.out.println(td);
            UserDayData d1 =   new UserDayData(1, td.getHour(), td.getType());
            //..и что дальше?
        }
        */

        //--ВАЖНО:
        //      - timedata.get(0).getId() возвращает идентификатор записи из Timedata, а нам нужен номер дня в месяце (для теста сойдет)
        //      - индексация в списке объектов Timedata начинается с 0
        //      - в целях тестирования, список объектов Timedata содержит ровно 31 запись по колву дней месяца, в реальной БД это не так;
        UserDayData d1 =  new UserDayData(timedata.get(0).getId(), timedata.get(0).getHour(), timedata.get(0).getType());
        UserDayData d2 =  new UserDayData(timedata.get(1).getId(), timedata.get(1).getHour(), timedata.get(1).getType());
        UserDayData d3 =  new UserDayData(timedata.get(2).getId(), timedata.get(2).getHour(), timedata.get(2).getType());
        UserDayData d4 =  new UserDayData(timedata.get(3).getId(), timedata.get(3).getHour(), timedata.get(3).getType());
        UserDayData d5 =  new UserDayData(timedata.get(4).getId(), timedata.get(4).getHour(), timedata.get(4).getType());
        UserDayData d6 =  new UserDayData(timedata.get(5).getId(), timedata.get(5).getHour(), timedata.get(5).getType());
        UserDayData d7 =  new UserDayData(timedata.get(6).getId(), timedata.get(6).getHour(), timedata.get(6).getType());
        UserDayData d8 =  new UserDayData(timedata.get(7).getId(), timedata.get(7).getHour(), timedata.get(7).getType());
        UserDayData d9 =  new UserDayData(timedata.get(8).getId(), timedata.get(8).getHour(), timedata.get(8).getType());
        UserDayData d10 = new UserDayData(timedata.get(9).getId(), timedata.get(9).getHour(), timedata.get(9).getType());
        UserDayData d11 = new UserDayData(timedata.get(10).getId(), timedata.get(10).getHour(), timedata.get(10).getType());
        UserDayData d12 = new UserDayData(timedata.get(11).getId(), timedata.get(11).getHour(), timedata.get(11).getType());
        UserDayData d13 = new UserDayData(timedata.get(12).getId(), timedata.get(12).getHour(), timedata.get(12).getType());
        UserDayData d14 = new UserDayData(timedata.get(13).getId(), timedata.get(13).getHour(), timedata.get(13).getType());
        UserDayData d15 = new UserDayData(timedata.get(14).getId(), timedata.get(14).getHour(), timedata.get(14).getType());

        UserDayData d16 = new UserDayData(timedata.get(15).getId(), timedata.get(15).getHour(), timedata.get(15).getType());
        UserDayData d17 = new UserDayData(timedata.get(16).getId(), timedata.get(16).getHour(), timedata.get(16).getType());
        UserDayData d18 = new UserDayData(timedata.get(17).getId(), timedata.get(17).getHour(), timedata.get(17).getType());
        UserDayData d19 = new UserDayData(timedata.get(18).getId(), timedata.get(18).getHour(), timedata.get(18).getType());
        UserDayData d20 = new UserDayData(timedata.get(19).getId(), timedata.get(19).getHour(), timedata.get(19).getType());
        UserDayData d21 = new UserDayData(timedata.get(20).getId(), timedata.get(20).getHour(), timedata.get(20).getType());
        UserDayData d22 = new UserDayData(timedata.get(21).getId(), timedata.get(21).getHour(), timedata.get(21).getType());
        UserDayData d23 = new UserDayData(timedata.get(22).getId(), timedata.get(22).getHour(), timedata.get(22).getType());
        UserDayData d24 = new UserDayData(timedata.get(23).getId(), timedata.get(23).getHour(), timedata.get(23).getType());
        UserDayData d25 = new UserDayData(timedata.get(24).getId(), timedata.get(24).getHour(), timedata.get(24).getType());
        UserDayData d26 = new UserDayData(timedata.get(25).getId(), timedata.get(25).getHour(), timedata.get(25).getType());
        UserDayData d27 = new UserDayData(timedata.get(26).getId(), timedata.get(26).getHour(), timedata.get(26).getType());
        UserDayData d28 = new UserDayData(timedata.get(27).getId(), timedata.get(27).getHour(), timedata.get(27).getType());
        UserDayData d29 = new UserDayData(timedata.get(28).getId(), timedata.get(28).getHour(), timedata.get(28).getType());
        UserDayData d30 = new UserDayData(timedata.get(29).getId(), timedata.get(29).getHour(), timedata.get(29).getType());
        UserDayData d31 = new UserDayData(timedata.get(30).getId(), timedata.get(30).getHour(), timedata.get(30).getType());

        return new UserMonthData(d1,d2,d3,d4,d5,d6,d7,d8,d9,d10,d11,d12,d13,d14,d15,d16,d17,d18,d19,d20,d21,d22,d23,d24,d25,d26,d27,d28,d29,d30,d31);
    }

    //--НЕУДАЧНАЯ версия: возвращается список Объектов типа UserDayData с данными по каждому дню,
    //  а нужно чтобы возвращался 1 комплексный объект с данными за весь месяц для возможности вывода в Excel шаблон
    /*
    private List<UserDayData> getMonthdata() {

        List<UserDayData> monthdatas = new ArrayList<>();

        UserDayData d1 =   new UserDayData(1, 8, "Я");
        UserDayData d2 =   new UserDayData(2, 8, "Я");
        UserDayData d3 =   new UserDayData(3, 8, "Я");
        UserDayData d4 =   new UserDayData(4, 8, "Я");
        UserDayData d5 =   new UserDayData(5, 0, "в");
        UserDayData d6 =   new UserDayData(6, 0, "в");
        UserDayData d7 =   new UserDayData(7, 8, "Я");
        UserDayData d8 =   new UserDayData(8, 8, "Я");
        UserDayData d9 =   new UserDayData(9, 8, "Я");
        UserDayData d10 = new UserDayData(10, 8, "Я");
        UserDayData d11 = new UserDayData(11, 8, "Я");
        UserDayData d12 = new UserDayData(12, 0, "в");
        UserDayData d13 = new UserDayData(13, 0, "в");
        UserDayData d14 = new UserDayData(14, 8, "Я");
        UserDayData d15 = new UserDayData(15, 8, "Я");

        UserDayData d16 = new UserDayData(16, 8, "Я");
        UserDayData d17 = new UserDayData(17, 8, "Я");
        UserDayData d18 = new UserDayData(18, 8, "Я");
        UserDayData d19 = new UserDayData(19, 0, "в");
        UserDayData d20 = new UserDayData(20, 0, "в");
        UserDayData d21 = new UserDayData(21, 8, "Я");
        UserDayData d22 = new UserDayData(22, 8, "Я");
        UserDayData d23 = new UserDayData(23, 8, "Я");
        UserDayData d24 = new UserDayData(24, 8, "Я");
        UserDayData d25 = new UserDayData(25, 8, "Я");
        UserDayData d26 = new UserDayData(26, 0, "в");
        UserDayData d27 = new UserDayData(27, 0, "в");
        UserDayData d28 = new UserDayData(28, 8, "Я");
        UserDayData d29 = new UserDayData(29, 8, "Я");
        UserDayData d30 = new UserDayData(30, 8, "Я");
        UserDayData d31 = new UserDayData(31, 7, "Я");

        monthdatas.add(d1);
        monthdatas.add(d2);
        monthdatas.add(d3);
        monthdatas.add(d4);
        monthdatas.add(d5);
        monthdatas.add(d6);
        monthdatas.add(d7);
        monthdatas.add(d8);
        monthdatas.add(d9);
        monthdatas.add(d10);
        monthdatas.add(d11);
        monthdatas.add(d12);
        monthdatas.add(d13);
        monthdatas.add(d14);
        monthdatas.add(d15);
        monthdatas.add(d16);
        monthdatas.add(d17);
        monthdatas.add(d18);
        monthdatas.add(d19);
        monthdatas.add(d20);
        monthdatas.add(d21);
        monthdatas.add(d22);
        monthdatas.add(d23);
        monthdatas.add(d24);
        monthdatas.add(d25);
        monthdatas.add(d26);
        monthdatas.add(d27);
        monthdatas.add(d28);
        monthdatas.add(d29);
        monthdatas.add(d30);
        monthdatas.add(d31);

        return monthdatas;
    }
    */

    //--ВОЗМОЖНО НЕУДАЧНАЯ ВЕРСИЯ: объект типа UserMonthData который возвращает этот Метод не связан с источником данных типа Timedata
    /*
    private UserMonthData getUserMonthData() {

        UserDayData d1 =   new UserDayData(1, 8, "Я");
        UserDayData d2 =   new UserDayData(2, 8, "Я");
        UserDayData d3 =   new UserDayData(3, 8, "Я");
        UserDayData d4 =   new UserDayData(4, 8, "Я");
        UserDayData d5 =   new UserDayData(5, 0, "в");
        UserDayData d6 =   new UserDayData(6, 0, "в");
        UserDayData d7 =   new UserDayData(7, 8, "Я");
        UserDayData d8 =   new UserDayData(8, 8, "Я");
        UserDayData d9 =   new UserDayData(9, 8, "Я");
        UserDayData d10 = new UserDayData(10, 8, "Я");
        UserDayData d11 = new UserDayData(11, 8, "Я");
        UserDayData d12 = new UserDayData(12, 0, "в");
        UserDayData d13 = new UserDayData(13, 0, "в");
        UserDayData d14 = new UserDayData(14, 8, "Я");
        UserDayData d15 = new UserDayData(15, 8, "Я");

        UserDayData d16 = new UserDayData(16, 8, "Я");
        UserDayData d17 = new UserDayData(17, 8, "Я");
        UserDayData d18 = new UserDayData(18, 8, "Я");
        UserDayData d19 = new UserDayData(19, 0, "в");
        UserDayData d20 = new UserDayData(20, 0, "в");
        UserDayData d21 = new UserDayData(21, 8, "Я");
        UserDayData d22 = new UserDayData(22, 8, "Я");
        UserDayData d23 = new UserDayData(23, 8, "Я");
        UserDayData d24 = new UserDayData(24, 8, "Я");
        UserDayData d25 = new UserDayData(25, 8, "Я");
        UserDayData d26 = new UserDayData(26, 0, "в");
        UserDayData d27 = new UserDayData(27, 0, "в");
        UserDayData d28 = new UserDayData(28, 8, "Я");
        UserDayData d29 = new UserDayData(29, 8, "Я");
        UserDayData d30 = new UserDayData(30, 8, "Я");
        UserDayData d31 = new UserDayData(31, 7, "Я");

        return new UserMonthData(d1,d2,d3,d4,d5,d6,d7,d8,d9,d10,d11,d12,d13,d14,d15,d16,d17,d18,d19,d20,d21,d22,d23,d24,d25,d26,d27,d28,d29,d30,d31);
    }
     */


    //--ОБНАРУЖЕНА ПРОБЛЕМА при работе с комплексными объектами при выводе в Excel шаблон -- в поле объекта должно быть конкретное значение, а не другой объект
    private static class UserMonthData {

        //-- data by days of month
        private final UserDayData d1,d2,d3,d4,d5,d6,d7,d8,d9,d10,d11,d12,d13,d14,d15,d16,d17,d18,d19,d20,d21,d22,d23,d24,d25,d26,d27,d28,d29,d30,d31;

        private UserMonthData(UserDayData d1, UserDayData d2, UserDayData d3, UserDayData d4, UserDayData d5,
                              UserDayData d6, UserDayData d7, UserDayData d8, UserDayData d9, UserDayData d10,
                              UserDayData d11, UserDayData d12, UserDayData d13, UserDayData d14, UserDayData d15,
                              UserDayData d16, UserDayData d17, UserDayData d18, UserDayData d19, UserDayData d20,
                              UserDayData d21, UserDayData d22, UserDayData d23, UserDayData d24, UserDayData d25,
                              UserDayData d26, UserDayData d27, UserDayData d28,
                              UserDayData d29, UserDayData d30, UserDayData d31)
        {
            this.d1 = d1;
            this.d2 = d2;
            this.d3 = d3;
            this.d4 = d4;
            this.d5 = d5;
            this.d6 = d6;
            this.d7 = d7;
            this.d8 = d8;
            this.d9 = d9;
            this.d10 = d10;
            this.d11 = d11;
            this.d12 = d12;
            this.d13 = d13;
            this.d14 = d14;
            this.d15 = d15;
            this.d16 = d16;
            this.d17 = d17;
            this.d18 = d18;
            this.d19 = d19;
            this.d20 = d20;
            this.d21 = d21;
            this.d22 = d22;
            this.d23 = d23;
            this.d24 = d24;
            this.d25 = d25;
            this.d26 = d26;
            this.d27 = d27;
            this.d28 = d28;
            this.d29 = d29;
            this.d30 = d30;
            this.d31 = d31;
        }
    }

    //--ОБНАРУЖЕНА ПРОБЛЕМА при работе с комплексными объектами при выводе в Excel шаблон -- в поле объекта должно быть конкретное значение, а не другой объект
    private static class UserDayData {

        private final Long d;           //-- day number -- тип Long для теста
        //private final Integer d;
        private final Integer h;        //-- hours
        private final String  p;        //-- presence flag

        //public UserDayData(Integer day, Integer hours, String present) {
        public UserDayData(Long day, Integer hours, String present) {
            this.d = day;
            this.h = hours;
            this.p = present;
        }
    }

}
