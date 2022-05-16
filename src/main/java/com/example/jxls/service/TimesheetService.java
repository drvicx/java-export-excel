package com.example.jxls.service;

import com.example.jxls.common.Report;
import com.example.jxls.dao.TimesheetDao;
//import com.example.jxls.dao.UserdataDao;          //-- НЕ_РАБОТАЕТ -- не выводится Timedata
//import com.example.jxls.dao.UserdataDaoV1;        //-- РАБОТАЕТ    -- выводятся поля единичного объекта Timedata
//import com.example.jxls.dao.UserdataDaoV2;        //-- РАБОТАЕТ    -- выводятся поля комплексного объекта User+Timedata
import com.example.jxls.dao.UserdataDaoV3;          //-- РАБОТАЕТ    -- выводится коллекция комплексных объектов User+Timedata

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import java.time.LocalDate;

//--DEBUG
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TimesheetService {

    //--DEBUG
    private static final Logger logger = LoggerFactory.getLogger(TimesheetService.class);


    //--FIELD DEPENDENCY INJECTION -- TimesheetDao
    //private TimesheetDao dao;                     //--WARN: Field 'dao' may be 'final'
    private final TimesheetDao dao;                 //--FIX
    //private final UserdataDao udao;               //--NEW: FIELD DEPENDENCY INJECTION -- TimesheetDao
    //private final UserdataDaoV1 udao;             //--NEW: 10.03.2022
    //private final UserdataDaoV2 udao;             //--NEW: 12.03.2022
    private final UserdataDaoV3 udao;               //--NEW: 12.03.2022

    //--Constructor parameter
    public TimesheetService() {
        dao = new TimesheetDao();
      //udao = new UserdataDao();                   //--NEW
      //udao = new UserdataDaoV1();                 //--NEW: 10.03.2022
      //udao = new UserdataDaoV2();                 //--NEW: 12.03.2022
        udao = new UserdataDaoV3();                 //--NEW: 12.04.2022
    }


    private void createCommonTimesheetReport(String templateName, String outputName) throws FileNotFoundException {

        LocalDate nowDate = LocalDate.now();

        Report report = new Report();
        OutputStream outStream = new FileOutputStream(outputName);
        Map<String, Object> data = new HashMap<>();

        data.put("rep", dao.getReportTitle());              //-- Single Object             -- rep: report
        //data.put("orgs", dao.getAllOrgdatas());           //-- List of Orgdata Objects   -- orgs: org data`s
        data.put("org", dao.getOrgdata());                  //-- Single  Orgdata Object    -- org: orgdata

        //--Separate User and Timedata Objects
        //data.put("users", dao.getAllUsers());             //-- List of User Objects      -- ud: user data
        //data.put("td", dao.getAllTimedata());             //-- List of Timedata Objects  -- td: time data

        //--Integrated User and Timedata Object (Userdata)
        //data.put("userdata", udao.getAllUsersData());     //--РАБОТАЕТ_ЧАСТИЧНО: возвращает список User объектов, вывод Timedata НЕ_РАБОТАЕТ
        //data.put("d", udao.getAllUsersData2());           //--НЕ_РАБОТАЕТ: возвращает комплексный объект со множеством полей

        //data.put("d", udao.getSingleDataV1());            //--РАБОТАЕТ: возвращает комплексный одиночный объект содержащий User и Timedata  (1 день) объекты;
        //data.put("d", udao.getSingleDataV2());            //--РАБОТАЕТ: возвращает комплексный одиночный объект содержащий User и Timedata (31 день) объекты;
        data.put("userdata", udao.getListDataV1());         //--РАБОТАЕТ: возвращает коллекцию Комплексных Объектов типа Userdata (содержат User и Timedata (31 день) объекты);

        //DEBUG-LOG -- была проблема с тем что Excel шаблон через поле "t" не видел поля вложенных объекта(ов) Timedata
        //System.out.println("=DEBUG1: " + udao.getAllUsersData2().getU().getFullNameAlias());                          //=DEBUG1: Фунт З.П.
        //System.out.println("=DEBUG: " + udao.getAllUsersData2().getT()...метод поля "t" не доступен);

        //data.put("createdAt", "2021-12-16");                                                                          //-- static Date
        data.put("repTS", nowDate);                                                                                     //-- dynamic Date  --repTS: report timestamp

        //--DEBUG
        //logger.debug("=DEBUG: TimesheetService: data" + data);

        //--Create report
        report.createDocument(outStream, templateName, data);
    }

    public void createTimesheetReport() throws FileNotFoundException {
        //createCommonTimesheetReport("timesheetTemplate", "target/timesheet.xlsx");                                    //--файл не открывается в Excel
        //createCommonTimesheetReport("timesheetTemplate20", "target/timesheet20_failed.xls");                          //--НЕ_РАБОТАЕТ: Timedata НЕ выводится, User выводится
        //createCommonTimesheetReport("timesheetTemplate21", "target/timesheet21_failed.xls");                          //--НЕ_РАБОТАЕТ: Timedata НЕ выводится, User выводится
        //createCommonTimesheetReport("timesheetTemplate22", "target/timesheet22_failed.xls");                          //--НЕ_РАБОТАЕТ: Timedata НЕ выводится, User выводится
        //createCommonTimesheetReport("timesheetTemplate23", "target/timesheet23_failed.xls");

        //createCommonTimesheetReport("timesheetTemplate24_singleDataV1", "target/timesheet24_singleDataV1.xls");       //--РАБОТАЕТ: выводятся единичные User и Timedata
        //createCommonTimesheetReport("timesheetTemplate25_singleDataV2", "target/timesheet25_singleDataV2.xls");       //--РАБОТАЕТ: выводится 1 строка содержащая User и Timedata данные;
        //createCommonTimesheetReport("timesheetTemplate26_listDataV1", "target/timesheet26_listDataV1.xls");           //--РАБОТАЕТ: выводится 1 строка комплексного Объекта содержащая User и Timedata данные;
        createCommonTimesheetReport("timesheetTemplate27_listDataV2", "target/timesheet27_listDataV2.xls");             //..В_РАБОТЕ
    }

}
