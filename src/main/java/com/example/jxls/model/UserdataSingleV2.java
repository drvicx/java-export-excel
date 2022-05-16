package com.example.jxls.model;

//--Класс User расположен на уровне текущего пакета "model", поэтому импорт не требуется
//--Класс TimesheetV2 расположен на уровне текущего пакета "model", поэтому импорт не требуется

import lombok.Getter;
import lombok.Setter;


/**
 *=КЛАСС КОТОРЫЙ СОБИРАЕТ ОБЪЕКТЫ СОДЕРЖАЩИЕ ДАННЫЕ КЛАССОВ User и Timedata -- версия-1
 * - в поле "u" - единичный объект типа User (все личные данные по конкретному сотруднику исключая отработанное время)
 * - в поле "t" - единичный но комплексный объект типа TimesheetV2 (данные по отработанному времени за весь месяц (31 день) для конкретного сотрудника)
 *
*/
@Getter
@Setter
public class UserdataSingleV2 {

    private User u;
    private TimesheetV2 t;

    //--CONSTRUCTOR
    public UserdataSingleV2(User user, TimesheetV2 timedata) {
        this.u = user;
        this.t = timedata;
    }

}
