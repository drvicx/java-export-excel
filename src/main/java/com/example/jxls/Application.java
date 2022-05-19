package com.example.jxls;

import com.example.jxls.service.TimesheetV3Service;		//--NEW: 2022.05.13 16:25

import java.io.FileNotFoundException;


public class Application {

	public static void main(String[] args) throws FileNotFoundException {

		//--Create Timesheet Report
		TimesheetV3Service serviceTimesheet = new TimesheetV3Service();		//--NEW: 2022.05.13 16:25

		serviceTimesheet.createTimesheetReport();
	}

}
