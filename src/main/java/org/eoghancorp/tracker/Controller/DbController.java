package org.eoghancorp.tracker.Controller;

import org.eoghancorp.tracker.Models.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
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


    public User getUser(String userId) {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Setup the connection with the DB
            // Result set get the result of the SQL query


            String sqlString = String.format("select * from tracker.users where userId='%s'", userId);

            resultSet = statement
                    .executeQuery(sqlString);
            // writeResultSet(resultSet);


            while(resultSet.next()) {
                // Get user information.
                UUID UserId = UUID.fromString(resultSet.getString("userId"));
                String UserName = resultSet.getString("userName");
                String Email = resultSet.getString("email");
                String Pass = resultSet.getString("pass");
                Date CreatedAt = resultSet.getDate("createdAt");

                return new User(UserId, UserName, Email, Pass, CreatedAt);

            }


        } catch (Exception e) {
            System.out.println("** ERROR: " + e.getMessage());
        } finally {
            close();
        }

        return new User();
    }

    public boolean createUser(User newUser) throws SQLException, ClassNotFoundException {
        // This will load the MySQL driver, each DB has its own driver
        Class.forName("com.mysql.cj.jdbc.Driver");


        return statement.execute(String.format(
                "INSERT INTO users(userId, userName, email, pass, createdAt) VALUES(%s, %s, %s, %s, %s)",
                newUser.getUserId().toString(),
                newUser.getUserName(),
                newUser.getEmail(),
                newUser.getPassword(),
                newUser.getCreatedAt().toString())
        );


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


