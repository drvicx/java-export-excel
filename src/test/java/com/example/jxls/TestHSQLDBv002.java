package com.example.jxls;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


public class TestHSQLDBv002 {

    public static void getTimesheetData() {

        //--параметры подключения
        Connection conn = null;
        String db = "jdbc:hsqldb:hsql://localhost:9000/timesheet-udm";
        String user = "SA";
        String psswd = "";

        //--Try-Catch блок
        try {
            //--создаем объект подключения
            conn = DriverManager.getConnection(db, user, psswd);

            //--создаем объекты содержащие sql-запрос и выполняем запрос
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from TIMESHEET");

            //--в цикле выводим все поля результата
            while(rs.next()) {
                System.out.println(rs.getString("USERID") +" "+ rs.getString("PERSONAL_NUMBER") +" "+ rs.getString("FULLNAME_ALIAS"));
            }
        }
        //--в случае ошибки перехватываем ее и выводим сообщение
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        //--в завершении - закрываем подключение с перехватом и выводом ошибки
        finally {
            try {
                if (conn != null)
                    conn.close();
            }
            catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    //throws java.sql.SQLException
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        // Вызываем функцию вывода данных из таблицы [TIMESHEET]
        getTimesheetData();
    }

    //=OUTPUT:
    /* *
     *    1 562 Фунт З.П.
     *    2 449 Бендер О.И.
     *    3 623 Балаганов Ш.П.
     *
     * */

    //=ИСТОЧНИКИ:
    /* *
    *   JDBC HSQLDB Tutorial
    *   https://examples.javacodegeeks.com/enterprise-java/sql-enterprise-java/jdbc-hsqldb-tutorial/
    *
    * */

}
