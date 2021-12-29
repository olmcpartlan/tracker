package org.eoghancorp.tracker.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eoghancorp.tracker.Models.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


public class DbController {
    private String tosh_connectinString = "jdbc:mysql://address=(host=localhost)(port=3306)(user=root)";
    private String book_connectinString = "jdbc:mysql://address=(host=localhost)(port=3306)(user=root)(password=naltrapcm)";
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public DbController() throws SQLException{
        connect = DriverManager
                .getConnection(tosh_connectinString);

        // Statements allow to issue SQL queries to the database
        statement = connect.createStatement();

        statement.execute("USE tracker;");

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
                Date UpdatedAt = resultSet.getDate("updatedAt");

                return new User(UserId, UserName, Email, Pass, CreatedAt, UpdatedAt);

            }


        } catch (Exception e) {
            System.out.println("** ERROR: \t" + e.getMessage());
            System.out.println("** AT:  \t" + e.getStackTrace());
        } finally {
            close();
        }

        return new User();
    }

    public User createUser(User newUser) throws SQLException, ClassNotFoundException, ParseException {
        // This will load the MySQL driver, each DB has its own driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        UUID id = UUID.randomUUID();

        // DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        LocalDateTime localDate = LocalDateTime.now();

        System.out.println(id);

        Date currentDateTime = Date.from(localDate.toInstant(ZoneOffset.UTC));


        String insertStatement = String.format(
                "INSERT INTO `tracker`.`users` (`user_id`, `user_name`, `user_email`, `user_pass`, `created_at`, `updated_at`)" +
                "VALUES('%s','%s','%s','%s','%s', '%s')",
                id,
                newUser.getUserName(),
                newUser.getEmail(),
                newUser.getPassword(),
                localDate,
                localDate
        );

        System.out.println(insertStatement);
        newUser.setUserId(id);
        newUser.setCreatedAt(currentDateTime);
        newUser.setUpdatedAt(currentDateTime);


        boolean errorCode = statement.execute(insertStatement);


        if(!errorCode) {
            return new User(id, newUser.getUserName(), newUser.getEmail(), newUser.getPassword(), currentDateTime, currentDateTime);
        }

        return new User("user not created because of a problem.", "", "");

    }


    /*
    INSERT INTO `tracker`.`FoodData` VALUES (<{relationId: }>, <{foodId: }>, <{userId: }>, <{quantity: }>, <{createdAt: }>, <{updatedAt: }>);
    */


    public boolean addFoodToUser(String userId, String foodId) throws SQLException{
        LocalDateTime localDate = LocalDateTime.now();

        String insertStatement = String.format( "INSERT INTO FoodData VALUES('%s','%s','%s','%s','%s','%s')",
                UUID.randomUUID(),
                foodId,
                userId,
                1,  // Quantity (not using right now)
                localDate,
                localDate

        );

        return statement.execute(insertStatement);



    }

    // Returns the user's food ids.
    public String[] getUserFood(String userId) throws SQLException {
        String query = String.format("SELECT foodId FROM FoodData WHERE userId = '%s'", userId);
        resultSet = statement.executeQuery(query);

        List<String> ids = new ArrayList<String>();

        while(resultSet.next()) {
            Object foodId = resultSet.getObject(0);
            System.out.println();
            // ids.add(foodId);
        }

        return (String[])ids.toArray();

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


