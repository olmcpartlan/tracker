package org.eoghancorp.tracker.Controller;

import com.sun.jdi.event.ExceptionEvent;
import org.eoghancorp.tracker.Models.User;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private DbController db;


    public UserController() {
        try {
            db = new DbController();
        }
        catch (Exception e) {
            System.out.println("\n\n***ERR: \n\n:" + e.getMessage());
        }
    }

    @GetMapping("/select")
    public User getUser(@RequestParam String userId) {
        User foundUser = db.getUser(userId);

        return foundUser;
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User newUser) {
        try
        {
            User created =  db.createUser(newUser);

            System.out.println("Created user: \t" + created.getUserName());

            return created;

        }
        catch(Exception e) {
            System.out.println("ERR: \t" + e.getMessage());
            System.out.println("AT: \t" + e.getStackTrace());
        }

        // TODO: this should be an exception.
        return new User();
    }

}
