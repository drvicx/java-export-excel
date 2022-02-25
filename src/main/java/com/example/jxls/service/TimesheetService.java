package com.example.jxls.service;

import com.example.jxls.common.Report;
import com.example.jxls.dao.TimesheetDao;      //-- NEW

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

    //private TimesheetDao dao;               //--WARN: Field 'dao' may be 'final'
    private final TimesheetDao dao;           //--FIX

    public TimesheetService() {
        dao = new TimesheetDao();
    }

    private void createCommonTimesheetReport(String templateName, String outputName) throws FileNotFoundException {

        LocalDate nowDate = LocalDate.now();

        Report report = new Report();
        OutputStream outStream = new FileOutputStream(outputName);

        Map<String, Object> data = new HashMap<>();
        data.put("orgdatas", dao.getAllOrgdatas());               //-- List of Orgdata Objects
        //data.put("orgdata", dao.getOrgdata());                  //-- Single  Orgdata Object
        data.put("users", dao.getAllUsers());                     //-- List of User Objects
        data.put("timedatas", dao.getAllTimedata());
        //data.put("createdAt", "2021-12-16");                    //-- static Date
        data.put("reportTS", nowDate);                            //-- dynamic Date

        //--DEBUG
        //logger.debug("=DEBUG: TimesheetService: data" + data);

        //--Create report
        report.createDocument(outStream, templateName, data);
    }

    public void createTimesheetReport() throws FileNotFoundException {
        //createCommonTimesheetReport("timesheetTemplate", "target/timesheet.xlsx");      //--файл не открывается в Excel
        createCommonTimesheetReport("timesheetTemplate", "target/timesheet.xls");
    }

}
