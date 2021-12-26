package org.eoghancorp.tracker.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class User {
    private UUID userId;
    private String userName;
    private String email;
    private String password;
    private Date createdAt;

    public User() { }

    public User(String username, String useremail, String password) {
        this.userId = UUID.randomUUID();
        this.userName = username;
        this.email = useremail;
        this.password = password;
        this.createdAt = new Date();

    }

}
