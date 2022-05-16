package com.example.jxls.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


/**
 *=КЛАСС КОТОРЫЙ СОБИРАЕТ ОБЪЕКТЫ ТИПА User и Timedata В ОДИН -- версия-2
 *
*/
@Getter
@Setter
public class Userdata2 {

    //private final User u;
    //private final UserMonthData t;
    private User u;
    private UserMonthData t;

    //--CONSTRUCTOR: version-2
    /*
    public Userdata2(User user, List<Timedata> timedata) {
        this.u = user;
        this.t = getUserMonthData(timedata);
    }
    */

    //--CONSTRUCTOR: version-3
    public Userdata2(User user, Timedata timedata) {
        this.u = user;
        this.t = getUserMonthData2(timedata);
        //DEBUG-LOG
        //System.out.println("=DEBUG2: " + this.t);
        //=DEBUG2: com.example.jxls.model.Userdata2$UserMonthData@61443d8f
        //=DEBUG2: com.example.jxls.model.Userdata2$UserMonthData@445b84c0
        //..т.е тут какие-то ссылки

        //System.out.println("=DEBUG3: " + this.u.getFullNameAlias());          //=DEBUG3: Фунт З.П.
        //System.out.println("=DEBUG3: " + this.t.d1h + ", " + this.t.d1p);     //=DEBUG3: 8, Я
    }

    //
    private UserMonthData getUserMonthData2(Timedata timedata) {

        UserMonthData umd = new UserMonthData(
                //d1h  d1p ..
                null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
                null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
                null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
                //d31h  d31p ..
                null,null,
                //hst  pst  ht   pt
                null,null,null,null
        );
        umd.d1h = timedata.getHour();    umd.d1p = timedata.getType();

        //DEBUG-LOG
        //System.out.println("=DEBUG4: " + umd.d1h);      //=DEBUG3: 8

        return umd;
    }


    //--ПРОБЛЕМА при выводе в Excel-шаблон: нет доступа к полям комлексных объектов, например: timedata.userdaydata.hours
    //        14:52:13.852 [main] DEBUG org.apache.commons.jexl3.JexlEngine - org.jxls.expression.JexlExpressionEvaluator.evaluate@1:4![3,6]: 'd.t.d1.h' unsolvable property 'd1'
    //        14:52:13.853 [main] DEBUG org.apache.commons.jexl3.JexlEngine - org.jxls.expression.JexlExpressionEvaluator.evaluate@1:4![3,5]: 'd.t.p' unsolvable property 'p'
    //        14:52:13.854 [main] DEBUG org.apache.commons.jexl3.JexlEngine - org.jxls.expression.JexlExpressionEvaluator.evaluate@1:4![3,6]: 'd.t.d2.h' unsolvable property 'd2'
    //
    private UserMonthData getUserMonthData(List<Timedata> timedata) {

        UserMonthData umd = new UserMonthData(
              //d1h  d1p ..
                null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
                null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
                null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
              //d31h  d31p ..
                null,null,
              //hst  pst  ht   pt
                null,null,null,null
        );
        //--начало месяца
        umd.d1h = 8;    umd.d1p = "О";     //-- 01.12.2020   --О: Отпуск
        umd.d2h = 8;    umd.d2p = "О";     //-- 02.12.2020
        umd.d3h = 8;    umd.d3p = "О";     //-- 03.12.2020
        umd.d4h = 8;    umd.d4p = "О";     //-- 04.12.2020
        umd.d5h = 0;    umd.d5p = "О";     //-- 05.12.2020
        umd.d6h = 0;    umd.d6p = "О";     //-- 06.12.2020
        umd.d7h = 8;    umd.d7p = "О";     //-- 07.12.2020
        umd.d8h = 8;    umd.d8p = "О";     //-- 08.12.2020
        umd.d9h = 8;    umd.d9p = "О";     //-- 09.12.2020
        umd.d10h = 8;   umd.d10p = "О";    //-- 10.12.2020
        umd.d11h = 8;   umd.d11p = "О";    //-- 11.12.2020
        umd.d12h = 0;   umd.d12p = "О";    //-- 12.12.2020
        umd.d13h = 0;   umd.d13p = "О";    //-- 13.12.2020
        umd.d14h = 8;   umd.d14p = "Я";    //-- 14.12.2020  --Я: Явка
        umd.d15h = 8;   umd.d15p = "Я";    //-- 15.12.2020
        //--половина месяца
        umd.d16h = 8;   umd.d16p = "Я";    //-- 16.12.2020
        umd.d17h = 8;   umd.d17p = "Я";    //-- 17.12.2020
        umd.d18h = 8;   umd.d18p = "Я";    //-- 18.12.2020
        umd.d19h = 0;   umd.d19p = "в";    //-- 19.12.2020
        umd.d20h = 0;   umd.d20p = "в";    //-- 20.12.2020
        umd.d21h = 8;   umd.d21p = "Я";    //-- 21.12.2020
        umd.d22h = 8;   umd.d22p = "Я";    //-- 22.12.2020
        umd.d23h = 8;   umd.d23p = "Я";    //-- 23.12.2020
        umd.d24h = 8;   umd.d24p = "Я";    //-- 24.12.2020
        umd.d25h = 8;   umd.d25p = "Я";    //-- 25.12.2020
        umd.d26h = 0;   umd.d26p = "в";    //-- 26.12.2020
        umd.d27h = 0;   umd.d27p = "в";    //-- 27.12.2020
        umd.d28h = 8;   umd.d28p = "Я";    //-- 28.12.2020
        umd.d29h = 8;   umd.d29p = "Я";    //-- 29.12.2020
        umd.d30h = 8;   umd.d30p = "Я";    //-- 30.12.2020
        umd.d31h = 7;   umd.d31p = "Я";    //-- 31.12.2020
        //--подытоги и итоги
        umd.hst = "Я 2, О 13";      umd.ht  = "Я 14, О 13";
        umd.pst = "Я 16, О 72";     umd.pt =  "Я 111, О 72";

        return umd;
    }

    //
    private static class UserMonthData {

        //--data by days of month
        private Integer d1h,d2h,d3h,d4h,d5h,d6h,d7h,d8h,d9h,d10h,d11h,d12h,d13h,d14h,d15h,d16h,d17h,d18h,d19h,d20h,d21h,d22h,d23h,d24h,d25h,d26h,d27h,d28h,d29h,d30h,d31h;
        private String  d1p,d2p,d3p,d4p,d5p,d6p,d7p,d8p,d9p,d10p,d11p,d12p,d13p,d14p,d15p,d16p,d17p,d18p,d19p,d20p,d21p,d22p,d23p,d24p,d25p,d26p,d27p,d28p,d29p,d30p,d31p;
        private String  hst,pst,ht,pt;
        //--hst: hours sub total, pst: presence sub total, ht: hours total, pt: presence total

        //--CONSTRUCTOR
        private UserMonthData(
                                Integer d1h,  String d1p,
                                Integer d2h,  String d2p,
                                Integer d3h,  String d3p,
                                Integer d4h,  String d4p,
                                Integer d5h,  String d5p,
                                Integer d6h,  String d6p,
                                Integer d7h,  String d7p,
                                Integer d8h,  String d8p,
                                Integer d9h,  String d9p,
                                Integer d10h, String d10p,
                                Integer d11h, String d11p,
                                Integer d12h, String d12p,
                                Integer d13h, String d13p,
                                Integer d14h, String d14p,
                                Integer d15h, String d15p,
                                Integer d16h, String d16p,
                                Integer d17h, String d17p,
                                Integer d18h, String d18p,
                                Integer d19h, String d19p,
                                Integer d20h, String d20p,
                                Integer d21h, String d21p,
                                Integer d22h, String d22p,
                                Integer d23h, String d23p,
                                Integer d24h, String d24p,
                                Integer d25h, String d25p,
                                Integer d26h, String d26p,
                                Integer d27h, String d27p,
                                Integer d28h, String d28p,
                                Integer d29h, String d29p,
                                Integer d30h, String d30p,
                                Integer d31h, String d31p,
                                String hst,   String pst,
                                String ht,    String pt
                              )
        {
            this.d1h = d1h;     this.d1p = d1p;
            this.d2h = d2h;     this.d2p = d2p;
            this.d3h = d3h;     this.d3p = d3p;
            this.d4h = d4h;     this.d4p = d4p;
            this.d5h = d5h;     this.d5p = d5p;
            this.d6h = d6h;     this.d6p = d6p;
            this.d7h = d7h;     this.d7p = d7p;
            this.d8h = d8h;     this.d8p = d8p;
            this.d9h = d9h;     this.d9p = d9p;
            this.d10h = d10h;   this.d10p = d10p;
            this.d11h = d11h;   this.d11p = d11p;
            this.d12h = d12h;   this.d12p = d12p;
            this.d13h = d13h;   this.d13p = d13p;
            this.d14h = d14h;   this.d14p = d14p;
            this.d15h = d15h;   this.d15p = d15p;
            this.d16h = d16h;   this.d16p = d16p;
            this.d17h = d17h;   this.d17p = d17p;
            this.d18h = d18h;   this.d18p = d18p;
            this.d19h = d19h;   this.d19p = d19p;
            this.d20h = d20h;   this.d20p = d20p;
            this.d21h = d21h;   this.d21p = d21p;
            this.d22h = d22h;   this.d22p = d22p;
            this.d23h = d23h;   this.d23p = d23p;
            this.d24h = d24h;   this.d24p = d24p;
            this.d25h = d25h;   this.d25p = d25p;
            this.d26h = d26h;   this.d26p = d26p;
            this.d27h = d27h;   this.d27p = d27p;
            this.d28h = d28h;   this.d28p = d28p;
            this.d29h = d29h;   this.d29p = d29p;
            this.d30h = d30h;   this.d30p = d30p;
            this.d31h = d31h;   this.d31p = d31p;
            //--total & subtotal by hours & presence flags
            this.hst = hst;     this.pst = pst;
            this.ht = ht;       this.pt = pt;
            //--hst: hours sub total, pst: presence sub total, ht: hours total, pt: presence total
        }
    }

    //--ОБНАРУЖЕНА ПРОБЛЕМА при работе с комплексными объектами при выводе в Excel шаблон -- в поле объекта должно быть конкретное значение, а не другой объект
    /*
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
    */

}
