package com.lucar01.aeroporto;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    private static Connection connection;

    public static Connection getConnection(){
        String databaseName = "aeroporto"; //TODO: add database name
        String databaseUser = "root"; //TODO: add database user
        String databasePassword = ""; //TODO:
        String url = "jdbc:mysql://localhost/" + databaseName; //TODO: add database url

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, databaseUser, databasePassword);
        }catch(Exception e){
            e.printStackTrace();
        }

        return connection;
    }
}
