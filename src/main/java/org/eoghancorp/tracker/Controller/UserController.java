package org.eoghancorp.tracker.Controller;

import org.eoghancorp.tracker.Models.User;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.UUID;

@RequestMapping("/api")
public class UserController {
    private DbController db;


    public UserController() throws SQLException {
        db = new DbController();
    }


    public User getUser(UUID userId) {
        return new User();
    }

}
