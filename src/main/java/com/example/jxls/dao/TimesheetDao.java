package com.example.jxls.dao;

import com.example.jxls.model.Orgdata;
import com.example.jxls.model.User;
import com.example.jxls.model.Timedata;
import com.example.jxls.model.ReportTitle;		//--NEW
import com.example.jxls.util.DateTimeUtil;		//--NEW 2022.03.09 15:00


//import javax.persistence.Column;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.time.LocalDate;
import java.util.Map;


/**
 *=DATA ACCESS OBJECT/CLASS FOR ENTITY CLASSES "Orgdata", "User", "Timedata"
 */
public class TimesheetDao {

	//--DEPENDENCY INJECTION
	//DateTimeUtil dtu = new DateTimeUtil();

	//--RETURNS REPORT TITLE DATA SINGLE OBJECT
	public ReportTitle getReportTitle() {

		ReportTitle reportTitle = new ReportTitle();

		//--REPORT TITLE INFO
		reportTitle.setId(1L);									//--01	ID				-- id				-- Идентификатор Отчета
		reportTitle.setTableNum("5/2");							//--02	TABLENUM		-- tableNum     	-- Табель №
		reportTitle.setPeriodFromDay(1L);						//--03	PERIOD_FROMDAY	-- periodFromDay	-- за период с  1 	 --номер дня месяца
		reportTitle.setPeriodToDay(31L);						//--04	PERIOD_TODAY	-- periodToDay		--           по 31 	 --номер дня месяца
		reportTitle.setPeriodMonthName("декабря");				//--05: PERIOD_MONTH	-- periodMonthName               декабря --название месяца
		reportTitle.setPeriodYear(2020L);						//--06: PERIOD_YEAR		-- periodYear             	     2020 г. --год отчета
		//reportTitle.setDate1(LocalDate.parse("2020-11-30"));	//--07: DATE1			-- date1            -- Дата
		//reportTitle.setDate2(LocalDate.parse("2020-12-23"));	//--08: DATE2			-- date2            -- Дата формирования документа
		reportTitle.setDate1("30.11.2020");
		reportTitle.setDate2("23.12.2020");
		//reportTitle.setEditNum(1L);							//--09: EDITNUM 		-- editNum          -- Номер корректировки
		reportTitle.setTableType(0L);							//--10: TABLETYPE		-- tableType        -- Вид табеля (первичный - 0; корректирующий - 1, 2, и т.д.)

		//--Return ReportTitle Object
		return reportTitle;
	}

	//--RETURNS SINGLE ORGDATA OBJECT
	public Orgdata getOrgdata() {

		Orgdata org = new Orgdata();
		//--dates:v4 -- вынес Методы работы с датой-временем в отдельный Инструментальный Класс
		//DateTimeUtil dtu = new DateTimeUtil();

		//--CONTORA
		org.setRecordId(1L);									//--01	ID 				-- recordId
		org.setOrgName("ООО Рога и копыта");					//--02	ORGNAME			-- orgName
		org.setDepName("Отдел по заготовке рогов и копыт");		//--03	DEPNAME			-- depName
		org.setOkud("123456789");								//--04	OKUD 			-- okud
		org.setOkpo("987654321");								//--05	OKPO 			-- okpo
		org.setDepBoss("З.П. Фунт");							//--06	DEPBOSS			-- depBoss
		org.setDepBossPos("Начальник");							//--07	DEPBOSS_POS		-- depBossPos
		org.setResponder("Ш.П. Балаганов");						//--08	RESPONDER		-- responder
		org.setResponderPos("Программист");						//--09	RESPONDER_POS	-- responderPos
		org.setLogo1("assets/images/logo_rik_1.png");			//--08	LOGO1			-- logo1
		org.setLogo2("assets/images/logo_rik_2.png");			//--09	LOGO2			-- logo2
		//--dates
		//org.setDateBegin(getCurrentDatePlus().get(0));		//--10	BEGINDATE		-- dateBegin	--текущая дата
		//org.setDateEnd(getCurrentDatePlus().get(1));			//--11	ENDDATE			-- dateEnd		--+ 1 день (контора однодневка)

		//--dates:v4.1 -- вынес Методы работы с датой-временем в отдельный Инструментальный Класс
		DateTimeUtil dtu = new DateTimeUtil();
		org.setDateBegin(dtu.getCurrentDatePlus().get(0));		//--10	BEGINDATE		-- dateBegin	--текущая дата
		org.setDateEnd(dtu.getCurrentDatePlus().get(1));		//--11	ENDDATE			-- dateEnd		--+ 1 день (контора однодневка)

		//--dates:v4.2 -- вынес объект для доступа в поле класса - ВОЗМОЖНО НЕ ПРАВИЛЬНО
	    //org.setDateBegin(dtu.getCurrentDatePlus().get(0));	//--10	BEGINDATE		-- dateBegin	--текущая дата
	    //org.setDateEnd(dtu.getCurrentDatePlus().get(1));		//--11	ENDDATE			-- dateEnd		--+ 1 день (контора однодневка)

		//--Return Orgdata Object
		return org;
	}


	//--RETURNS LIST OF ORGDATA OBJECTS
	public List<Orgdata> getAllOrgdatas() {
		
		List<Orgdata> orgdatas = new ArrayList<>();

		//LocalDate nowDate = LocalDate.now();
		//LocalDate nowDatePlus1Day = nowDate.plusDays(1);

		//--dates:v4.1 -- вынес Методы работы с датой-временем в отдельный Инструментальный Класс
		DateTimeUtil dtu = new DateTimeUtil();

		//--CONTORA 1
		Orgdata org1 = new Orgdata();
		org1.setRecordId(1L);
		org1.setOrgName("ООО Рога и копыта");
		org1.setDepName("Отдел по заготовке рогов и копыт");
		org1.setOkud("123456789");
		org1.setOkpo("987654321");
		org1.setDepBoss("Фунт ЗП");
		org1.setDepBossPos("Начальник");
		org1.setResponder("Балаганов ШП");
		org1.setResponderPos("Программист");
		org1.setLogo1("assets/images/logo_rik_1.png");
		org1.setLogo2("assets/images/logo_rik_2.png");
		//--dates:v1
		//org1.setDateBegin(LocalDate.parse("2021-06-21"));
		//org1.setDateEnd(LocalDate.parse("2021-06-22"));
		//--dates:v2
		//org1.setDateBegin(nowDate);
		//org1.setDateEnd(nowDatePlus1Day);
		//--dates:v3
		//org1.setDateBegin(getCurrentDatePlus().get(0));			//--текущая дата
		//org1.setDateEnd(getCurrentDatePlus().get(1));				//--текущая дата + 1 день (контора однодневка)
		//--dates:v4.1
		org1.setDateBegin(dtu.getCurrentDatePlus().get(0));			//--текущая дата
		org1.setDateEnd(dtu.getCurrentDatePlus().get(1));			//--текущая дата + 1 день (контора однодневка)

		//--CONTORA 2
		Orgdata org2 = new Orgdata();
		org2.setRecordId(2L);
		org2.setOrgName("ЗАО Геркулес");
		org2.setDepName("Финансовый отдел");
		org2.setOkud("322411786");
		org2.setOkpo("654123987");
		org2.setDepBoss("Полыхаев НП");							//--Полыхаев Николай Петрович --точное ФИО неизвестно
		org2.setResponder("Берлага ЕБ");						//--Берлага Евгений Борисович --точное ФИО неизвестно
		org2.setLogo1("assets/images/logo_hcl_1.png");
		org2.setLogo2("assets/images/logo_hcl_2.png");
		//--dates:v1
		//org2.setDateBegin(LocalDate.parse("2021-06-21"));
		//org2.setDateEnd(LocalDate.parse("0001-01-01"));
		//--dates:v2
		org2.setDateBegin(getDatesKV().get("zero"));
		org2.setDateEnd(getDatesKV().get("end"));

		//--Add Objects to Array List
		orgdatas.add(org1);
		orgdatas.add(org2);

		//--Return Array List of Orgdata Objects
		return orgdatas;
	}


	//--RETURNS LIST OF USER OBJECTS
	public List<User> getAllUsers() {

		List<User> users = new ArrayList<>();
		//--dates:v4.1 -- вынес Методы работы с датой-временем в отдельный Инструментальный Класс
		DateTimeUtil dtu = new DateTimeUtil();

		//--User1
		User user1 = new User();
		user1.setUserId(1L);									//--01	ID 				-- userId 			-- Long
		user1.setPersonalNumber(562L);							//--02	PERSONALNUMBER 	-- personalNumber 	-- Long
		user1.setFirstName("Зиц");								//--03	FIRSTNAME 		-- firstName		-- String
		user1.setMiddleName("Председатель");					//--04	MIDLENAME		-- middleName 		-- String
		user1.setLastName("Фунт");								//--05	LASTNAME		-- lastName 		-- String
		user1.setFullNameAlias("Фунт З.П.");					//--06	FULLNAMEALIAS	-- fullNameAlias 	-- String
	  //user1.setBirthDate(getDateFromString("1902-09-20"));	//--07	BIRTHDATE		-- birthDate 		-- LocalDate
		user1.setBirthDate(dtu.getDateFromString("1902-09-20"));	//--07	BIRTHDATE		-- birthDate 		-- LocalDate
		user1.setLogin("funt_zp");								//--08	LOGIN 			-- login 			-- String
		user1.setPositionEng("boss");							//--09	POSITION_E 		-- positionEng 		-- String
		user1.setPositionRus("Начальник");						//--10	POSITION_R 		-- positionRus		-- String
		user1.setPhoneWork("408562");							//--11	PHONE_WORK 		-- phoneWork		-- String
		user1.setPhonePers("79135625662");						//--12	PHONE_PERS 		-- phonePers		-- String
		user1.setEmailWork("pers562@rik.ru"); 					//--13	EMAIL_WORK 		-- emailWork		-- String
		user1.setEmailPers("funt_zp@gmail.com");				//--14	EMAIL_PERS 		-- emailPers		-- String
		user1.setPhotoLink("assets/images/users/561_photo.png");							//--14	PHOTO_LINK 		-- photoLink		-- String
		user1.setSocialLink1("https://ru.wikipedia.org/wiki/Зицпредседатель_Фунт");			//--15	SOCIAL_LINK1 	-- socialLink1		-- String
		user1.setSocialLink2("https://ru.wikipedia.org/wiki/Павленко,_Павел_Павлович");		//--16	SOCIAL_LINK2 	-- socialLink2		-- String
		user1.setSocialLink3(null);															//--17	SOCIAL_LINK3 	-- socialLink3		-- String
		user1.setAccessLevel("head");							//--18	ACCESSLEVEL 	-- accessLevel		-- String
      //user1.setHireDate(getDateFromString("2017-01-01"));		//--19	HIRED 			-- hireDate			-- LocalDate
      //user1.setFireDate(getDateFromString("2021-06-30"));		//--20	FIRED 			-- fireDate			-- LocalDate
		//--dates:v4.1
		user1.setHireDate(dtu.getDateFromString("2017-01-01"));
		user1.setFireDate(dtu.getDateFromString("2021-06-30"));


		//--User2
		User user2 = new User();
		user2.setUserId(2L);
		user2.setPersonalNumber(449L);
		user2.setFirstName("Остап");
		user2.setMiddleName("Ибрагимович");
		user2.setLastName("Бендер");
		user2.setFullNameAlias("Бендер О.И.");
      //user2.setBirthDate(getDateFromString("1935-03-16"));
		user2.setBirthDate(dtu.getDateFromString("1935-03-16"));			//--dates:v4.1
		user2.setLogin("bender_oi");
		user2.setPositionEng("creator");
		user2.setPositionRus("Организатор");
		user2.setPhoneWork("408449");
		user2.setPhonePers("79134494449");
		user2.setEmailWork("pers449@rik.ru");
		user2.setEmailPers("bender_oi@gmail.com");
		user2.setPhotoLink("assets/images/users/449_photo.jpg");
		user2.setSocialLink1("https://ru.wikipedia.org/wiki/Остап_Бендер");
		user2.setSocialLink2("https://ru.wikipedia.org/wiki/Юрский,_Сергей_Юрьевич");
		user2.setSocialLink3(null);
		user2.setAccessLevel("admin");
	  //user2.setHireDate(getDateFromString("2017-01-01"));
		user2.setHireDate(dtu.getDateFromString("2017-01-01"));				//--dates:v4.1
		user2.setFireDate(null);

		//--User3
		User user3 = new User();
		user3.setUserId(3L);
		user3.setPersonalNumber(623L);
		user3.setFirstName("Шура");
		user3.setMiddleName("Петрович");
		user3.setLastName("Балаганов");
		user3.setFullNameAlias("Балаганов Ш.П.");
	  //user3.setBirthDate(getDateFromString("1936-10-08"));
		user3.setBirthDate(dtu.getDateFromString("1936-10-08"));			//--dates:v4.1
		user3.setLogin("balagan_off");
		user3.setPositionEng("programmer");
		user3.setPositionRus("Программист");
		user3.setPhoneWork("408623");
		user3.setPhonePers("79136236223");
		user3.setEmailWork("pers623@rik.ru");
		user3.setEmailPers("balagan_off@gmail.com");
		user3.setPhotoLink("assets/images/users/623_photo.jpg");
		user3.setSocialLink1("https://ru.wikipedia.org/wiki/Шура_Балаганов");
		user3.setSocialLink2("https://ru.wikipedia.org/wiki/Куравлёв,_Леонид_Вячеславович");
		user3.setSocialLink3(null);
		user3.setAccessLevel("user");
	  //user3.setHireDate(getDateFromString("2017-01-01"));
		user3.setHireDate(dtu.getDateFromString("2017-01-01"));				//--dates:v4.1
		user3.setFireDate(null);

		//--User4
		User user4 = new User();
		user4.setUserId(4L);
		user4.setPersonalNumber(655L);
		user4.setFirstName("Михаил");
		user4.setMiddleName("Самуэлевич");
		user4.setLastName("Паниковский");
		user4.setFullNameAlias("Паниковский М.С.");
	  //user4.setBirthDate(getDateFromString("1916-09-21"));
		user4.setBirthDate(dtu.getDateFromString("1916-09-21"));		//--dates:v4.1
		user4.setLogin("panic_ms");
		user4.setPositionEng("programmer");
		user4.setPositionRus("Программист");
		user4.setPhoneWork("408655");
		user4.setPhonePers("79136556555");
		user4.setEmailWork("pers655@rik.ru");
		user4.setEmailPers("panic_ms@gmail.com");
		user4.setPhotoLink("assets/images/users/655_photo.jpg");
		user4.setSocialLink1("https://ru.wikipedia.org/wiki/Паниковский");
		user4.setSocialLink2("https://ru.wikipedia.org/wiki/Гердт,_Зиновий_Ефимович");
		user4.setSocialLink3(null);
		user4.setAccessLevel("user");
	  //user4.setHireDate(getDateFromString("2017-01-01"));
		user4.setHireDate(dtu.getDateFromString("2017-01-01"));			//--dates:v4.1
		user4.setFireDate(null);

		//--Add Objects to Array List
		users.add(user1);
		users.add(user2);
		users.add(user3);
		users.add(user4);

		//--Return Array List of User Objects
		return users;
	}


	//--RETURNS LIST OF TIMEDATA OBJECTS
	public List<Timedata> getAllTimedata() {

		List<Timedata> timedatas = new ArrayList<>();
		//--dates:v4.1 -- вынес Методы работы с датой-временем в отдельный Инструментальный Класс
		DateTimeUtil dtu = new DateTimeUtil();

		//--User1 Day1 Timedata
		//Timedata tdUser1Day1 = new Timedata();
		//tdUser1Day1.setId(1L);									//--1	ID 		-- id		-- Long
		//tdUser1Day1.setUserId(1L);								//--2	USERID 	-- userId 	-- Long
		//tdUser1Day1.setDate(getDateFromString("2020-12-01"));		//--5	DATE 	-- date 	-- LocalDate
		//tdUser1Day1.setHour(8);									//--3	HOUR 	-- hour 	-- Integer
		//tdUser1Day1.setType("Я");									//--4	TYPE_ 	-- type 	-- String

		//--User1 Day2 Timedata
		//Timedata tdUser1Day2 = new Timedata();
		//tdUser1Day2.setId(2L);
		//tdUser1Day2.setUserId(1L);
		//tdUser1Day2.setDate(getDateFromString("2020-12-02"));
		//tdUser1Day2.setHour(8);
		//tdUser1Day2.setType("Я");
		//..
		//..так задолбаешься все данные добавлять.. делаем в цикле

		//--автоматически генерируем Объекты Timedata (t) по кол-ву Сотрудников (u) и добавляем их список
		//--Timedata record ID
		long globalRecordId = 0L;

		for (int u = 0; u < 4; u++) {
			//--Dynamic User ID
			Long userId = (long) u + 1;

			for (int t = 0; t < 3; t++) {
				Timedata td = new Timedata();
				globalRecordId += 1L;										 	//-- dynamic Timedata ID (global)
				//Long recordId = (long) (t + 1);                            	//-- dynamic Timedata ID (local)
				String tdDay = String.format("%02d", t + 1);                 	//-- dynamic Day
				//LocalDate tdDate = getDateFromString("2020-12-" + tdDay);  	//-- dynamic Timedata Date
				LocalDate tdDate = dtu.getDateFromString("2020-12-" + tdDay);  	//--dates:v4.1
				//Long userId = 1L;                                          	//-- static User ID

				//td.setId(recordId);
				td.setId(globalRecordId);
				td.setUserId(userId);											//-- dynamic User ID
				//td.setDate(getDateFromString("2020-12-01"));					//-- static Date
				td.setDate(tdDate);                                         	//-- dynamic Date
				td.setHour(8);
				td.setType("Я");

				timedatas.add(td);
			}
		}

		//--Add Objects to Array List
		//timedatas.add(tdUser1Day1);

		//--Return Array List of User Objects
		return timedatas;

		/*
		//--User1 Day3 Timedata
		Timedata tdUser1Day3 = new Timedata();
		tdUser1Day3.setId(3L);
		tdUser1Day3.setUserId(1L);
		tdUser1Day3.setDate(getDateFromString("2020-12-03"));
		tdUser1Day3.setHour(8);
		tdUser1Day3.setType("Я");

		//--User1 Day4 Timedata
		Timedata tdUser1Day4 = new Timedata();
		tdUser1Day4.setId(4L);
		tdUser1Day4.setUserId(1L);
		tdUser1Day4.setDate(getDateFromString("2020-12-04"));
		tdUser1Day4.setHour(8);
		tdUser1Day4.setType("Я");
		*/

		//--EXAMPLE DATA OUTPUT
		/*
			640	1	2020-12-03	8	Я
			641	1	2020-12-04	8	Я
			642	1	2020-12-05	0	в
			643	1	2020-12-06	0	в
			644	1	2020-12-07	8	Я
			645	1	2020-12-08	8	Я
			646	1	2020-12-09	8	Я
			647	1	2020-12-10	8	Я
			648	1	2020-12-11	8	Я
			649	1	2020-12-12	0	в
			650	1	2020-12-13	0	в
			651	1	2020-12-14	8	Я
			652	1	2020-12-15	8	Я
			653	1	2020-12-16	8	Я
			654	1	2020-12-17	8	Я
			655	1	2020-12-18	8	Я
			656	1	2020-12-19	0	в
			657	1	2020-12-20	0	в
			658	1	2020-12-21	8	Я
			659	1	2020-12-22	8	Я
			660	1	2020-12-23	8	Я
			661	1	2020-12-24	8	Я
			662	1	2020-12-25	8	Я
			663	1	2020-12-26	0	в
			664	1	2020-12-27	0	в
			665	1	2020-12-28	8	Я
			666	1	2020-12-29	8	Я
			667	1	2020-12-30	8	Я
			668	1	2020-12-31	7	Я
			..
		 */
	}


	/*
	//--DATE GETTERS--

	//--RETURNS NOW DATE: for example: "2021-12-16"
	private LocalDate getCurrentDate() {
		return LocalDate.now();
	}

	//--RETURNS LocalDate from String: for example: "2021-12-16"
	private LocalDate getDateFromString(String stringDate) {
		return LocalDate.parse(stringDate);
	}

	//--RETURNS LIST OF 2 DATES - NOW & NOW+1d: for example: ["2021-12-16", "2021-12-17"]
	private List<LocalDate> getCurrentDatePlus() {

		List<LocalDate> dates = new ArrayList<>();

		LocalDate nowDate = LocalDate.now();
		LocalDate nowDatePlus1Day = nowDate.plusDays(1);

		dates.add(nowDate);
		dates.add(nowDatePlus1Day);

		return dates;
	}
	*/

	//--RETURNS KEY-VALUE HASHMAP OF 2 DATES - NOW & NOW+1d: for example: {"now": "2021-12-16", "nowPlus1Day": "2021-12-17"}
	private Map<String, LocalDate> getDatesKV() {

		Map<String, LocalDate> dates = new HashMap<String, LocalDate>();

		dates.put("zero", LocalDate.parse("1900-01-01"));
		dates.put("now", LocalDate.now());
		dates.put("tomorrow", LocalDate.now().plusDays(1));
		dates.put("begin", LocalDate.parse("2020-01-01"));
		dates.put("end", LocalDate.parse("2035-12-31"));

		return dates;
	}

}
