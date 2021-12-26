package org.eoghancorp.tracker.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;


public class DbController {
    private String connectinString = "jdbc:mysql://address=(host=localhost)(port=3306)(user=root)(password=naltrapcm)";
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public DbController() throws SQLException{
        connect = DriverManager
                .getConnection(connectinString);

        // Statements allow to issue SQL queries to the database
        statement = connect.createStatement();

    }


    public void getUser(UUID userId) {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Setup the connection with the DB
            // Result set get the result of the SQL query
            resultSet = statement
                    .executeQuery("select * from tracker.users where userId=''");
            writeResultSet(resultSet);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }

    }

    private void writeResultSet(ResultSet resultSet) throws SQLException {
        // ResultSet is initially before the first data set
        while (resultSet.next()) {
            // It is possible to get the columns via name
            // also possible to get the columns via the column number
            // which starts at 1
            // e.g. resultSet.getSTring(2);
            String userName = resultSet.getString("userName");
            System.out.println("***************\n\n\n\n");
            System.out.println("User: " + userName);
            System.out.println("\n\n\n\n***************");
        }
    }

    // You need to close the resultSet
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }


}


