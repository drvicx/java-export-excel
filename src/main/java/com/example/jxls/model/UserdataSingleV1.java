package com.example.jxls.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


/**
 *=КЛАСС КОТОРЫЙ СОБИРАЕТ ОБЪЕКТЫ СОДЕРЖАЩИЕ ДАННЫЕ КЛАССОВ User и Timedata -- версия-1
 * - в поле "u" - единичный объект типа User (все личные данные по конкретному сотруднику исключая отработанное время)
 * - в поле "t" - единичный объект типа Timedata (данные по отработанному времени за 1 день для конкретного сотрудника)
 *
*/
@Getter
@Setter
public class UserdataSingleV1 {

    private User u;
    private Timedata t;

    //--CONSTRUCTOR
    public UserdataSingleV1(User user, Timedata timedata) {
        this.u = user;
        this.t = timedata;
    }

}
