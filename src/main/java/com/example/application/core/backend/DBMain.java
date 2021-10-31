package com.example.application.core.backend;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.TimeZone;

@Service
public class DBMain {

    private Connection connection = null;
    private String user;
    private String password;
    private String url;
    private final MysqlDataSource source;


    private DBMain() {
        user = "root";
        password = "test";
        url = "jdbc:mysql://mysql_db:3306/company";
        source = new MysqlDataSource();
        source.setUser(user);
        source.setPassword(password);
        source.setURL(url);
        try {
            source.setServerTimezone(TimeZone.getDefault().getID());
            source.setCharacterEncoding("UTF-8");
            source.setAllowMultiQueries(true); //makes very vulnerable
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PreparedStatement preparedStatement(String sql) throws SQLException {
        if(connection == null){
            connect();
        }
        return connection.prepareStatement(sql);
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        connect();
        Statement stmt = connection.createStatement();
        return stmt.executeQuery(sql);
    }

    public int executeUpdate(String sql) throws SQLException {
        connect();
        Statement stmt = connection.createStatement();
        return stmt.executeUpdate(sql);
    }


    public void connect() throws SQLException {
        if (connection != null) {
            connection.close();
        }
        connection = source.getConnection();
    }

    public void closeQuietly(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            // quiet
        }
    }

    public void closeQuietly(Statement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            // quiet
        }
    }

}
