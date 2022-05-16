package com.example.jxls.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;


/**
 *=ENTITY CLASS "TimesheetV3"
 * - данные об отработанному сотрудником времени за весь месяц по дням (31 день);
 * - Класс для создания объектов с данными полученными из результсета хранимой процедуры sp_getTimesheetDataV3(year, month);
 *
 */
@Data
@Entity
@Table(name="TIMESHEET")
public class TimesheetV3 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    //--USER DATA
    @Column(name="USERID")
    private Integer userId;

    @Column(name="FULLNAME_ALIAS")
    private String fullnameAlias;

    @Column(name="PERSONAL_NUMBER")
    private Integer personalNumber;

    @Column(name="POSITION_RUS")
    private String positionRus;


    //--USERTIME DATA

    //--day 1 of 31
    @Column(name="D1H")
    private Integer d1h;        //-- h:hours    -- кол-во засчитанных отработанных часов
    @Column(name="D1P")
    private String d1p;         //-- p:presence -- флаг присутствия на рабочем месте

    //--day 2 of 31
    @Column(name="D2H")
    private Integer d2h;
    @Column(name="D2P")
    private String d2p;

    //--day 3 of 31
    @Column(name="D3H")
    private Integer d3h;
    @Column(name="D3P")
    private String d3p;

    //--day 4 of 31
    @Column(name="D4H")
    private Integer d4h;
    @Column(name="D4P")
    private String d4p;

    //--day 5 of 31
    @Column(name="D5H")
    private Integer d5h;
    @Column(name="D5P")
    private String d5p;

    //--day 6 of 31
    @Column(name="D6H")
    private Integer d6h;
    @Column(name="D6P")
    private String d6p;

    //--day 7 of 31
    @Column(name="D7H")
    private Integer d7h;
    @Column(name="D7P")
    private String d7p;

    //--day 8 of 31
    @Column(name="D8H")
    private Integer d8h;
    @Column(name="D8P")
    private String d8p;

    //--day 9 of 31
    @Column(name="D9H")
    private Integer d9h;
    @Column(name="D9P")
    private String d9p;

    //--day 10 of 31
    @Column(name="D10H")
    private Integer d10h;
    @Column(name="D10P")
    private String d10p;

    //--day 11 of 31
    @Column(name="D11H")
    private Integer d11h;
    @Column(name="D11P")
    private String d11p;

    //--day 12 of 31
    @Column(name="D12H")
    private Integer d12h;
    @Column(name="D12P")
    private String d12p;

    //--day 13 of 31
    @Column(name="D13H")
    private Integer d13h;
    @Column(name="D13P")
    private String d13p;

    //--day 14 of 31
    @Column(name="D14H")
    private Integer d14h;
    @Column(name="D14P")
    private String d14p;

    //--day 15 of 31
    @Column(name="D15H")
    private Integer d15h;
    @Column(name="D15P")
    private String d15p;

    //--day 16 of 31
    @Column(name="D16H")
    private Integer d16h;
    @Column(name="D16P")
    private String d16p;

    //--day 17 of 31
    @Column(name="D17H")
    private Integer d17h;
    @Column(name="D17P")
    private String d17p;

    //--day 18 of 31
    @Column(name="D18H")
    private Integer d18h;
    @Column(name="D18P")
    private String d18p;

    //--day 19 of 31
    @Column(name="D19H")
    private Integer d19h;
    @Column(name="D19P")
    private String d19p;

    //--day 20 of 31
    @Column(name="D20H")
    private Integer d20h;
    @Column(name="D20P")
    private String d20p;

    //--day 21 of 31
    @Column(name="D21H")
    private Integer d21h;
    @Column(name="D21P")
    private String d21p;

    //--day 22 of 31
    @Column(name="D22H")
    private Integer d22h;
    @Column(name="D22P")
    private String d22p;

    //--day 23 of 31
    @Column(name="D23H")
    private Integer d23h;
    @Column(name="D23P")
    private String d23p;

    //--day 24 of 31
    @Column(name="D24H")
    private Integer d24h;
    @Column(name="D24P")
    private String d24p;

    //--day 25 of 31
    @Column(name="D25H")
    private Integer d25h;
    @Column(name="D25P")
    private String d25p;

    //--day 26 of 31
    @Column(name="D26H")
    private Integer d26h;
    @Column(name="D26P")
    private String d26p;

    //--day 27 of 31
    @Column(name="D27H")
    private Integer d27h;
    @Column(name="D27P")
    private String d27p;

    //--day 28 of 31
    @Column(name="D28H")
    private Integer d28h;
    @Column(name="D28P")
    private String d28p;

    //--day 29 of 31
    @Column(name="D29H")
    private Integer d29h;
    @Column(name="D29P")
    private String d29p;

    //--day 30 of 31
    @Column(name="D30H")
    private Integer d30h;
    @Column(name="D30P")
    private String d30p;

    //--day 31 of 31
    @Column(name="D31H")
    private Integer d31h;
    @Column(name="D31P")
    private String d31p;

    //--15 & 31 days (sub)total by hours and type
    @Column(name="T15H")
    private String t15h;
    @Column(name="T15P")
    private String t15p;

    @Column(name="T31H")
    private String t31h;
    @Column(name="T31P")
    private String t31p;

    //--Year&Month of Timesheet Record
    @Column(name="TS_YEAR")
    private Integer tsYear;

    @Column(name="TS_MONTH")
    private Integer tsMonth;

    @Column(name="TS")
    private Timestamp ts;

}
