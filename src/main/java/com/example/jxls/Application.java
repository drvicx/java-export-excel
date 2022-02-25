package com.example.jxls;

//import com.example.jxls.service.CommonService;
import com.example.jxls.service.TimesheetService;		//--NEW

import java.io.FileNotFoundException;


public class Application {

	public static void main(String[] args) throws FileNotFoundException {

		//--Create Client Report
		//CommonService service = new CommonService();
		//service.createClientReport();
		//service.createClientReportWithConditions();

		//--Create Timesheet Report
		TimesheetService serviceTimesheet = new TimesheetService();		//--NEW
		serviceTimesheet.createTimesheetReport();						//--NEW
	}

}
