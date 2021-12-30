package org.eoghancorp.tracker.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eoghancorp.tracker.Models.User;
import org.eoghancorp.tracker.Models.UserFood;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.*;
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


        Date currentDateTime = Date.from(localDate.toInstant(ZoneOffset.UTC));
        String hashedPassword = User.encryptPassword(newUser.getPassword());

        String insertStatement = String.format(
                "INSERT INTO `tracker`.`users` (`user_id`, `user_name`, `user_email`, `user_pass`, `created_at`, `updated_at`)" +
                        "VALUES('%s','%s','%s','%s','%s', '%s')",
                id,
                newUser.getUserName(),
                newUser.getEmail(),
                hashedPassword,
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


    public User loginUser(String userName, String passAttempt) throws SQLException {

        String query = String.format("SELECT user_name, user_email, user_pass from users WHERE user_name='%s'", userName);

        resultSet = statement.executeQuery(query);
        User foundUser = new User();

        if(resultSet.next()) {
            String foundPass = resultSet.getString("user_pass");

            if(passAttempt.equals(foundPass)) {


                foundUser = new User(
                        resultSet.getString("user_name"),
                        resultSet.getString("user_email"),
                        "");
                return foundUser;
            }
            else {
                // Password attempt was incorrect.
            }
        }

        else {
            // Nothing matched in the db.
            System.out.println();
        }


        return new User();
    }


    // Returns the user's food ids.
    public List<UserFood> getUserFood(String userId) throws SQLException {
        String query = String.format("SELECT foodId, quantity, createdAt FROM FoodData WHERE userId = '%s'", userId);
        resultSet = statement.executeQuery(query);


        List<UserFood> foods = new ArrayList<UserFood>();


        while(resultSet.next()) {
            String foodId = resultSet.getString("foodId");
            double quantity = resultSet.getDouble("quantity");
            Date createdAt = resultSet.getDate("createdAt");

            foods.add(new UserFood(foodId, "", quantity, createdAt));

            System.out.println();
            // ids.add(foodId);
        }


        return foods;


    }

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


