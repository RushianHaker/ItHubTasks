package org.example.db_access;

import org.example.Main;

import java.sql.*;

public class DatabaseUtils {
    public Statement getStatement() throws SQLException {
        return con.createStatement();
    }

    public PreparedStatement getPreparedStatement(String sql) throws SQLException {
        return con.prepareStatement(sql);
    }

    private static final String url = "jdbc:mysql://localhost:3306/"+ Main.DB_NAME+"?useUnicode=true&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "root";

    private Connection con = null;

    private static DatabaseUtils instance = null;
    public static DatabaseUtils getInstance(){

        if(instance == null) {
            instance = new DatabaseUtils();
        }
        return instance;
    }

    private DatabaseUtils(){
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            stmt = con.createStatement();

            // executing test query
            rs = stmt.executeQuery("select 1");
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
//        finally {
//            //close connection ,stmt and result set here
//            try { con.close(); } catch(SQLException se) { se.printStackTrace(); /*can't do anything */ }
//            try { stmt.close(); } catch(SQLException se) { se.printStackTrace(); /*can't do anything */ }
//            try { rs.close(); } catch(SQLException se) { se.printStackTrace(); /*can't do anything */ }
//        }
    }
}
