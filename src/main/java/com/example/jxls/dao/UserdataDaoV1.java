package com.example.jxls.dao;

import com.example.jxls.model.User;
import com.example.jxls.model.Timedata;
import com.example.jxls.model.UserdataSingleV1;
import com.example.jxls.util.DateTimeUtil;


/**
 *=DATA ACCESS OBJECT/CLASS FOR INTEGRATED DATA "User" and "Timedata"
 * - проверено, работает!
 */
public class UserdataDaoV1 {

    //--RETURNS OBJECT WITH MIXED DATA
    public UserdataSingleV1 getSingleDataV1() {

        //--Объект Инструментального Класса для работы с датой-временем (v4.1)
        DateTimeUtil dtu = new DateTimeUtil();

        //--Single User Object
        User u = new User();
        u.setUserId(1L);									//--01	ID 				-- userId 			-- Long
        u.setPersonalNumber(562L);							//--02	PERSONALNUMBER 	-- personalNumber 	-- Long
        u.setFullNameAlias("Фунт З.П.");					//--06	FULLNAMEALIAS	-- fullNameAlias 	-- String
        u.setPositionRus("Начальник");						//--10	POSITION_R 		-- positionRus		-- String

        //--Single Timedata Object
        Timedata td = new Timedata();
        td.setId(1L);                                       //--01  ID              -- id               -- Long
        td.setUserId(1L);                                   //--02  USERID          -- userId           -- Long
        td.setDate(dtu.getDateFromString("2020-12-01"));    //--05  DATE            -- date             -- LocalDate
        td.setHour(8);                                      //--03  HOUR            -- hour             -- Integer
        td.setType("Я");                                    //--04  TYPE            -- type             -- String

        //--Return Userdata Object (User + Timedata)
        return new UserdataSingleV1(u, td);
    }

}
