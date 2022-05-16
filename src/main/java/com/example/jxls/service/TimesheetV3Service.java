package com.example.jxls.service;

import com.example.jxls.common.Report;
import com.example.jxls.dao.TimesheetV3Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


public class TimesheetV3Service {

    //--DEBUG LOGGER
    private static final Logger logger = LoggerFactory.getLogger(TimesheetV3Service.class);

    //--FIELD DEPENDENCY INJECTION -- TimesheetDao
    private final TimesheetV3Dao tsV3dao;                 //--NEW: 2022.05.13 16:25

    //--Constructor parameter
    public TimesheetV3Service() {
        tsV3dao = new TimesheetV3Dao();                   //--NEW: 2022.05.13 16:25
    }


    private void createCommonTimesheetReport(String templateName, String outputName) throws FileNotFoundException {

        LocalDate nowDate = LocalDate.now();

        Report report = new Report();
        OutputStream outStream = new FileOutputStream(outputName);
        Map<String, Object> data = new HashMap<>();

        //data.put("userdata", tsV3dao.getListDataTest1());     //--возвращает список Объектов TimesheetV3  (содержат статические User и Timedata данные за 31 день);
                                                                //  который помещается в поле данных "userdata" для передачи в поток создания Excel документа

        //tsV3dao.getTimesheetDataFromHSQLDB();                         //..тест1: просто вызываем новый метод и смотрим что выводится в консоль
        data.put("userdata", tsV3dao.getTimesheetDataFromHSQLDB());     //..тест2: возвращает список Объектов TimesheetV3 созданных по данным полученным из БД;

        data.put("repTS", nowDate);                             //--dynamic Date  --repTS: report timestamp

        //--DEBUG
        //logger.debug("=DEBUG: TimesheetService: data" + data);

        //--Create report
        report.createDocument(outStream, templateName, data);
    }

    public void createTimesheetReport() throws FileNotFoundException {
        //createCommonTimesheetReport("timesheetTemplate27_listDataV2", "target/timesheet27_listDataV2.xls");   //..этот шаблон уже не подходит
        createCommonTimesheetReport("timesheetTemplate30_listDataV3", "target/timesheet30_listDataV3.xls");     //--NEW: 2022.05.13 16:25
    }

}
