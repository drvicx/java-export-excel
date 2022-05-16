package com.example.jxls.dao;

import com.example.jxls.model.User;
import com.example.jxls.model.Timedata;
//import com.example.jxls.model.Userdata;
import com.example.jxls.model.Userdata2;
import com.example.jxls.util.DateTimeUtil;

//import java.util.List;
//import java.util.ArrayList;
//import java.util.HashMap;

import java.time.LocalDate;


/**
 *=DATA ACCESS OBJECT/CLASS FOR INTEGRATED ENTITY CLASSES "User" and "Timedata"
 */
public class UserdataDao {

    //--RETURNS USERDATA OBJECT
    public Userdata2 getAllUsersData2() {

        //--dates:v4.1 -- вынес Методы работы с датой-временем в отдельный Инструментальный Класс
        DateTimeUtil dtu = new DateTimeUtil();

        //--List of Userdata Objects (User + Timedata)
        //List<Userdata2> userdatas = new ArrayList<>();

        //--Single User Object
        User user = new User();
        user.setUserId(1L);									    //--01	ID 				-- userId 			-- Long
        user.setPersonalNumber(562L);							//--02	PERSONALNUMBER 	-- personalNumber 	-- Long
        user.setFullNameAlias("Фунт З.П.");					    //--06	FULLNAMEALIAS	-- fullNameAlias 	-- String
        user.setPositionRus("Начальник");						//--10	POSITION_R 		-- positionRus		-- String

        /*
        //--List of Timedata Objects
        //Timedata timedata = new Timedata();
        //List<Timedata> timedatas = new ArrayList<>();

        //--автоматически генерируем Объекты Timedata (t) для 1 Сотрудника (u) и добавляем их список
        //--Timedata record ID
        //long globalRecordId = 0L;
        //--Single User ID
        Long userId = (long) 1;

        //for (int t = 0; t < 3; t++) { .. }             //-- (!) создается всего 3 Объекта типа Timedata (данные за 3 дня)
        for (int t = 0; t < 31; t++) {

            Timedata td = new Timedata();
            Long recordId = (long) (t + 1);                                  //-- dynamic Timedata ID (local)
            String tdDay = String.format("%02d", t + 1);                     //-- dynamic Day

            LocalDate tdDate = dtu.getDateFromString("2020-12-" + tdDay);    //-- dynamic Timedata Date: dates:v4.1

            td.setId(recordId);
            td.setUserId(userId);										//-- dynamic User ID
            //td.setDate(getDateFromString("2020-12-01"));				//-- static Date
            td.setDate(tdDate);                                         //-- dynamic Date
            td.setHour(8);
            td.setType("Я");

            timedatas.add(td);
        }
        */

        //
        Timedata td = new Timedata();
        td.setId(1L);
        td.setUserId(1L);
        td.setHour(8);
        td.setType("Я");
        td.setDate(dtu.getDateFromString("2020-12-01"));

        //DEBUG-LOG
        //System.out.println("=DEBUG: " + td.getHour());

        //--Return Userdata Objects
        return new Userdata2(user, td);
    }


    //--RETURNS LIST OF USERDATA OBJECTS
    /*
    public List<Userdata> getAllUsersData() {

        //--dates:v4.1 -- вынес Методы работы с датой-временем в отдельный Инструментальный Класс
        DateTimeUtil dtu = new DateTimeUtil();

        //--List of Userdata Objects (User + Timedata)
        List<Userdata> userdatas = new ArrayList<>();

        //--Single User Object
        User user = new User();
        user.setUserId(1L);									    //--01	ID 				-- userId 			-- Long
        user.setPersonalNumber(562L);							//--02	PERSONALNUMBER 	-- personalNumber 	-- Long
        user.setFullNameAlias("Фунт З.П.");					    //--06	FULLNAMEALIAS	-- fullNameAlias 	-- String
        user.setPositionRus("Начальник");						//--10	POSITION_R 		-- positionRus		-- String

        //--List of Timedata Objects
        Timedata timedata = new Timedata();
        List<Timedata> timedatas = new ArrayList<>();

        //--автоматически генерируем Объекты Timedata (t) для 1 Сотрудника (u) и добавляем их список
        //--Timedata record ID
        //long globalRecordId = 0L;
        //--Single User ID
        Long userId = (long) 1;

        //for (int t = 0; t < 3; t++) { .. }             //-- (!) создается всего 3 Объекта типа Timedata (данные за 3 дня)
        for (int t = 0; t < 31; t++) {

            Timedata td = new Timedata();
            Long recordId = (long) (t + 1);                                  //-- dynamic Timedata ID (local)
            String tdDay = String.format("%02d", t + 1);                     //-- dynamic Day

            LocalDate tdDate = dtu.getDateFromString("2020-12-" + tdDay);    //-- dynamic Timedata Date: dates:v4.1

            td.setId(recordId);
            td.setUserId(userId);										//-- dynamic User ID
            //td.setDate(getDateFromString("2020-12-01"));				//-- static Date
            td.setDate(tdDate);                                         //-- dynamic Date
            td.setHour(8);
            td.setType("Я");

            timedatas.add(td);
        }

        //--Create Userdata Object
        Userdata userdata = new Userdata(user, timedatas);

        //--Create List of Userdata Objects
        userdatas.add(userdata);

        //--Return Array List of Userdata Objects
        return userdatas;
    }
    */

}
