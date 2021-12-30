package org.eoghancorp.tracker.Models;

import lombok.Getter;
import lombok.Setter;

import java.security.MessageDigest;
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
    private Date updatedAt;

    public User() { }

    public User(String userName, String userEmail, String password) {
        this.userId = UUID.randomUUID();
        this.userName = userName;
        this.email = userEmail;
        this.password = password;
        this.createdAt = new Date();
        this.updatedAt = new Date();

    }

    public User(UUID userId, String username, String useremail, String password, Date createdAt, Date updatedAt) {
        this.userId = userId;
        this.userName = username;
        this.email = useremail;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

    }

    public static String encryptPassword(String inputPassword) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA");

            byte[] bytes = inputPassword.getBytes();
            md.reset();

            byte[] digestedByes = md.digest(bytes);
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < md.digest().length; i++) {
                if ((digestedByes[i] & 0xff) < 0x10) {
                    sb.append("0");
                }

                sb.append(Long.toString(digestedByes[i] & 0xff, 16));
            }

            return sb.toString();
        }
        catch (Exception e) {
            System.out.println("Exception thrown while encrypting. ");
            System.out.println(e.getMessage());
        }

        return "an error occurred";
    }

}
