package com.example.jxls;

//import com.example.jxls.service.CommonService;
//import com.example.jxls.service.TimesheetService;		//--OLD
import com.example.jxls.service.TimesheetV3Service;		//--NEW: 2022.05.13 16:25

import java.io.FileNotFoundException;


public class Application {

	public static void main(String[] args) throws FileNotFoundException {

		//--Create Client Report
		//CommonService service = new CommonService();
		//service.createClientReport();
		//service.createClientReportWithConditions();

		//--Create Timesheet Report
		//TimesheetService serviceTimesheet = new TimesheetService();		//--OLD
		TimesheetV3Service serviceTimesheet = new TimesheetV3Service();		//--NEW: 2022.05.13 16:25

		serviceTimesheet.createTimesheetReport();
	}

}
