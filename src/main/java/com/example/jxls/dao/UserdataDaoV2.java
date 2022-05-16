package com.example.jxls.dao;

import com.example.jxls.model.TimesheetV2;
import com.example.jxls.model.User;
import com.example.jxls.model.UserdataSingleV2;
import com.example.jxls.util.DateTimeUtil;


/**
 *=DATA ACCESS OBJECT/CLASS FOR INTEGRATED DATA "User" and "Timedata"
 * - ..
 */
public class UserdataDaoV2 {

    //--RETURNS OBJECT WITH MIXED DATA
    public UserdataSingleV2 getSingleDataV2() {

        //--Объект Инструментального Класса для работы с датой-временем (v4.1)
        DateTimeUtil dtu = new DateTimeUtil();

        //--Single User Object
        User u = new User();
        u.setUserId(1L);									//-- ID 				-- userId 			-- Long
        u.setPersonalNumber(562L);							//-- PERSONALNUMBER 	-- personalNumber 	-- Long
        u.setFullNameAlias("Фунт З.П.");					//-- FULLNAMEALIAS	    -- fullNameAlias 	-- String
        u.setPositionRus("Начальник");						//-- POSITION_R 		-- positionRus		-- String

        //--Single TimesheetV2 Object
        TimesheetV2 td = new TimesheetV2();
        td.setId(1L);                                       //-- ID                 -- id               -- Long
        td.setUserId(1L);                                   //-- USERID             -- userId           -- Long

        //--1я половина месяца                              //-- D1H, D1P           -- d1h, d1p         -- Integer, String
        td.setD1h(8);    td.setD1p("О");                    //-- 01.12.2020         -- О: Отпуск
        td.setD2h(8);    td.setD2p("О");                    //-- 02.12.2020
        td.setD3h(8);    td.setD3p("О");                    //-- 03.12.2020
        td.setD4h(8);    td.setD4p("О");                    //-- 04.12.2020
        td.setD5h(8);    td.setD5p("О");                    //-- 05.12.2020
        td.setD6h(8);    td.setD6p("О");                    //-- 06.12.2020
        td.setD7h(8);    td.setD7p("О");                    //-- 07.12.2020
        td.setD8h(8);    td.setD8p("О");                    //-- 08.12.2020
        td.setD9h(8);    td.setD9p("О");                    //-- 09.12.2020
        td.setD10h(8);   td.setD10p("О");                   //-- 10.12.2020
        td.setD11h(8);   td.setD11p("О");                   //-- 11.12.2020
        td.setD12h(0);   td.setD12p("О");                   //-- 12.12.2020
        td.setD13h(0);   td.setD13p("О");                   //-- 13.12.2020
        td.setD14h(8);   td.setD14p("Я");                   //-- 14.12.2020         -- Я: Явка
        td.setD15h(8);   td.setD15p("Я");                   //-- 15.12.2020

        //--подытог за 15 дней по часам (h) и флагам присутствия (p)
        td.setT15h("Я 2, О 13");    td.setT15p("Я 16, О 72");

        //--2я половина месяца
        td.setD16h(8);   td.setD16p("Я");                   //-- 16.12.2020
        td.setD17h(8);   td.setD17p("Я");                   //-- 17.12.2020
        td.setD18h(8);   td.setD18p("Я");                   //-- 18.12.2020
        td.setD19h(0);   td.setD19p("в");                   //-- 19.12.2020         -- в: Выходной
        td.setD20h(0);   td.setD20p("в");                   //-- 20.12.2020
        td.setD21h(8);   td.setD21p("Я");                   //-- 21.12.2020
        td.setD22h(8);   td.setD22p("Я");                   //-- 22.12.2020
        td.setD23h(8);   td.setD23p("Я");                   //-- 23.12.2020
        td.setD24h(8);   td.setD24p("Я");                   //-- 24.12.2020
        td.setD25h(8);   td.setD25p("Я");                   //-- 25.12.2020
        td.setD26h(0);   td.setD26p("в");                   //-- 26.12.2020
        td.setD27h(0);   td.setD27p("в");                   //-- 27.12.2020
        td.setD28h(8);   td.setD28p("Я");                   //-- 28.12.2020
        td.setD29h(8);   td.setD29p("Я");                   //-- 29.12.2020
        td.setD30h(8);   td.setD30p("Я");                   //-- 30.12.2020
        td.setD31h(7);   td.setD31p("Я");                   //-- 31.12.2020

        //--итог за 31 день по часам (h) и флагам присутствия (p)
        td.setT31h("Я 14, О 13");    td.setT31p("Я 111, О 72");

        //--Return Userdata Object (User + Timedata)
        return new UserdataSingleV2(u, td);
    }

}
