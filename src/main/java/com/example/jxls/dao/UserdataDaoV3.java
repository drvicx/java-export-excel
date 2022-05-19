package com.example.jxls.dao;

import com.example.jxls.model.TimesheetV2;
import com.example.jxls.model.User;
import com.example.jxls.model.UserdataSingleV2;
import com.example.jxls.util.DateTimeUtil;

//--NEW:2022.04.12
import java.util.Collection;
import java.util.LinkedList;


/**
 *=DATA ACCESS OBJECT/CLASS FOR INTEGRATED DATA "User" and "Timedata"
 * - ..
 */
public class UserdataDaoV3 {

    //--RETURNS OBJECT WITH MIXED DATA
    public Collection<Object> getListDataV1() {

        //--Объект Инструментального Класса для работы с датой-временем (v4.1)
        DateTimeUtil dtu = new DateTimeUtil();

        //--так нельзя.. тип в параметризации может быть только ОДИН
        //Collection<User, TimesheetV2> userdataList = new LinkedList<>();

        //--Коллекция которая может содержать разные типы (зная что все типы наследуются от Object)
        //--Список объектов разных типов
        //  https://stackoverflow.com/questions/19602601/create-an-arraylist-with-multiple-object-types
        //  *ответ от 2013.10.26
        Collection<Object> userdataList = new LinkedList<>();


        //--ФОРМИРОВАНИЕ КОМПЛЕКСНОГО ОБЪЕКТА #1------------------------------------------------------------------------

        //--Single User Object
        User u1 = new User();
        u1.setUserId(1L);									//-- ID 				-- userId 			-- Long
        u1.setPersonalNumber(562L);							//-- PERSONALNUMBER 	-- personalNumber 	-- Long
        u1.setFullNameAlias("Фунт З.П.");					//-- FULLNAMEALIAS	    -- fullNameAlias 	-- String
        u1.setPositionRus("Начальник");						//-- POSITION_R 		-- positionRus		-- String

        //--Single TimesheetV2 Object
        TimesheetV2 td1 = new TimesheetV2();
        td1.setId(1L);                                       //-- ID                 -- id               -- Long
        td1.setUserId(1L);                                   //-- USERID             -- userId           -- Long

        //--1я половина месяца                                //-- D1H, D1P          -- d1h, d1p         -- Integer, String
        td1.setD1h(8);    td1.setD1p("О");                    //-- 01.12.2020        -- О: Отпуск
        td1.setD2h(8);    td1.setD2p("О");                    //-- 02.12.2020
        td1.setD3h(8);    td1.setD3p("О");                    //-- 03.12.2020
        td1.setD4h(8);    td1.setD4p("О");                    //-- 04.12.2020
        td1.setD5h(8);    td1.setD5p("О");                    //-- 05.12.2020
        td1.setD6h(8);    td1.setD6p("О");                    //-- 06.12.2020
        td1.setD7h(8);    td1.setD7p("О");                    //-- 07.12.2020
        td1.setD8h(8);    td1.setD8p("О");                    //-- 08.12.2020
        td1.setD9h(8);    td1.setD9p("О");                    //-- 09.12.2020
        td1.setD10h(8);   td1.setD10p("О");                   //-- 10.12.2020
        td1.setD11h(8);   td1.setD11p("О");                   //-- 11.12.2020
        td1.setD12h(0);   td1.setD12p("О");                   //-- 12.12.2020
        td1.setD13h(0);   td1.setD13p("О");                   //-- 13.12.2020
        td1.setD14h(8);   td1.setD14p("Я");                   //-- 14.12.2020         -- Я: Явка
        td1.setD15h(8);   td1.setD15p("Я");                   //-- 15.12.2020

        //--подытог за 15 дней по часам (h) и флагам присутствия (p)
        td1.setT15h("Я 2, О 13");    td1.setT15p("Я 16, О 72");

        //--2я половина месяца
        td1.setD16h(8);   td1.setD16p("Я");                   //-- 16.12.2020
        td1.setD17h(8);   td1.setD17p("Я");                   //-- 17.12.2020
        td1.setD18h(8);   td1.setD18p("Я");                   //-- 18.12.2020
        td1.setD19h(0);   td1.setD19p("в");                   //-- 19.12.2020         -- в: Выходной
        td1.setD20h(0);   td1.setD20p("в");                   //-- 20.12.2020
        td1.setD21h(8);   td1.setD21p("Я");                   //-- 21.12.2020
        td1.setD22h(8);   td1.setD22p("Я");                   //-- 22.12.2020
        td1.setD23h(8);   td1.setD23p("Я");                   //-- 23.12.2020
        td1.setD24h(8);   td1.setD24p("Я");                   //-- 24.12.2020
        td1.setD25h(8);   td1.setD25p("Я");                   //-- 25.12.2020
        td1.setD26h(0);   td1.setD26p("в");                   //-- 26.12.2020
        td1.setD27h(0);   td1.setD27p("в");                   //-- 27.12.2020
        td1.setD28h(8);   td1.setD28p("Я");                   //-- 28.12.2020
        td1.setD29h(8);   td1.setD29p("Я");                   //-- 29.12.2020
        td1.setD30h(8);   td1.setD30p("Я");                   //-- 30.12.2020
        td1.setD31h(7);   td1.setD31p("Я");                   //-- 31.12.2020

        //--итог за 31 день по часам (h) и флагам присутствия (p)
        td1.setT31h("Я 14, О 13");    td1.setT31p("Я 111, О 72");

        //--формируем Комплексный единичный Объект (SingleObject - SO) содержащий данные User и Timedata
        UserdataSingleV2 userdataObj1 = new UserdataSingleV2(u1, td1);


        //--ФОРМИРОВАНИЕ КОМПЛЕКСНОГО ОБЪЕКТА #1------------------------------------------------------------------------

        //--Single User Object
        User u2 = new User();
        u2.setUserId(2L);
        u2.setPersonalNumber(333L);
        u2.setFullNameAlias("Паниковский М.С.");
        u2.setPositionRus("Программист");

        //--Single TimesheetV2 Object
        TimesheetV2 td2 = new TimesheetV2();
        td2.setId(2L);
        td2.setUserId(2L);

        //--1я половина месяца
        td2.setD1h(8);    td2.setD1p("Я");
        td2.setD2h(8);    td2.setD2p("Я");
        td2.setD3h(8);    td2.setD3p("Я");
        td2.setD4h(8);    td2.setD4p("Я");
        td2.setD5h(8);    td2.setD5p("Я");
        td2.setD6h(8);    td2.setD6p("Я");
        td2.setD7h(8);    td2.setD7p("Я");
        td2.setD8h(8);    td2.setD8p("Я");
        td2.setD9h(8);    td2.setD9p("Я");
        td2.setD10h(8);   td2.setD10p("Я");
        td2.setD11h(8);   td2.setD11p("Я");
        td2.setD12h(0);   td2.setD12p("Я");
        td2.setD13h(0);   td2.setD13p("Я");
        td2.setD14h(8);   td2.setD14p("О");
        td2.setD15h(8);   td2.setD15p("О");

        //--подытог за 15 дней по часам (h) и флагам присутствия (p)
        td2.setT15h("Я 13, О 2");    td2.setT15p("Я 72, О 16");

        //--2я половина месяца
        td2.setD16h(8);   td2.setD16p("О");
        td2.setD17h(8);   td2.setD17p("О");
        td2.setD18h(8);   td2.setD18p("О");
        td2.setD19h(0);   td2.setD19p("в");
        td2.setD20h(0);   td2.setD20p("в");
        td2.setD21h(8);   td2.setD21p("О");
        td2.setD22h(8);   td2.setD22p("О");
        td2.setD23h(8);   td2.setD23p("О");
        td2.setD24h(8);   td2.setD24p("О");
        td2.setD25h(8);   td2.setD25p("О");
        td2.setD26h(0);   td2.setD26p("в");
        td2.setD27h(0);   td2.setD27p("в");
        td2.setD28h(8);   td2.setD28p("О");
        td2.setD29h(8);   td2.setD29p("О");
        td2.setD30h(8);   td2.setD30p("О");
        td2.setD31h(7);   td2.setD31p("О");

        //--итог за 31 день по часам (h) и флагам присутствия (p)
        td2.setT31h("Я 13, О 14");    td2.setT31p("Я 72, О 111");

        //--формируем Комплексный единичный Объект (SingleObject - SO) содержащий данные User и Timedata
        UserdataSingleV2 userdataObj2 = new UserdataSingleV2(u2, td2);


        //--добавляем созданные Комплексные Объекты в Коллекцию
        userdataList.add(userdataObj1);
        userdataList.add(userdataObj2);


        //--Возвращаем Коллекцию комплексных объектов Userdata (User + Timedata)
        //  [userdataObj, userdataObj, userdataObj, ..]
        return userdataList;
    }

}
