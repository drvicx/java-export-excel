package com.example.jxls;

import java.sql.Connection;
import java.sql.DriverManager;


public class TestHSQLDBv001 {

    //--TEST1
    public static void main(String args[]) throws java.sql.SQLException {

        // --Проверка доступности необходимого класса JDBC драйвера
        /*
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver" );
        } catch (Exception e) {
            System.err.println("ERROR: failed to load HSQLDB JDBC driver.");
            e.printStackTrace();
            //return;
        }
        */
        //=OUTPUT:
        // - если библиотека с JDBC драйвером не подключена или ее не видно, то будет ошибка
        //   ERROR: failed to load HSQLDB JDBC driver.
        //   Java.lang.ClassNotFoundException: org.hsqldb.jdbc.JDBCDriver
        //
        //=FIX:
        // - в конфигурации зависимостей Maven проекта (pom.xml) подключена зависимость
        //   groupId: org.hsqldb, version: 2.5.1, artifactId: hsqldb
        //   после чего ошибка исчезла и Класс драйвера стал доступен в текущем Классе TestHSQLDBv001


        // --ВЫПОЛНЯЕМ ТЕСТОВЫЙ ЗАПРОС
        //Connection conn = DriverManager.getConnection("jdbc:default:connection");
        Connection conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9000/timesheet-udm");
        // ОШИБКА: No suitable driver found for jdbc:hsqldb:hsql://localhost:9000/timesheet-udm
        //    FIX: см. выше

        java.sql.Statement stmt = conn.createStatement();

        java.sql.ResultSet rs = stmt.executeQuery("select id, userid, fullname_alias from TIMESHEET");
        java.sql.ResultSetMetaData meta = rs.getMetaData();

        int cols  = meta.getColumnCount();
        System.out.println(cols);                 //=OUTPUT: 3

        rs.close();
        stmt.close();
    }

    //--TEST0
    /*
    public static void main(String args[]) {
        System.out.println("Hello Java");
    }
    */

    //=ИСТОЧНИКИ:
    /* *
    *   HSQLDB Guide -- Chapter 9. SQL-Invoked Routines
    *   https://hsqldb.org/doc/guide/sqlroutines-chapt.html
    *
    * */

}
