/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package athina;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.*;

/**
 *
 * @author apostoles
 */
public class Database {

    static String driverClassName = "com.mysql.jdbc.Driver";
    static String port = "3306";
    static String db = "athina";
    static String host = "127.0.0.1";
    static String url = "jdbc:mysql://"+host+":"+port+"/"+db;
    static Connection dbConnection = null;
    static String username = "root";
    static String passwd = "root";
    static Statement statement = null;


    static void initStatement() throws Exception {
        Class.forName(driverClassName);
        dbConnection = DriverManager.getConnection(url, username, passwd);
        statement = dbConnection.createStatement();
    }
}
