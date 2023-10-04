/**
 The UserInfo class is a configuration class that represents user information in the system.
 @Entity annotation is used to indicate that this class represents a table in a database.
 @Data is a convenient annotation that generates all the boilerplate code required for getters and setters methods.
 @AllArgsConstructor and
 @NoArgsConstructor annotations are used to generate constructors with and without arguments respectively.
 The class has four instance variables, including id, name, password, and roles.
 @Id and
 @GeneratedValue annotations, indicating that id is the primary key of the table and
 its value will be generated automatically. The name and password fields store
 the user's credentials and the roles field represents the user's roles in the system.
 The class provides two public methods, getPassword and getName, which return the password
 and name fields, respectively.
 @author Matthew Suprunov
 @version 1.0
 @since 20/04/2023
 */
package com.example.config;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String password;
    private String roles;
    /**
     * Returns the password field value of the UserInfo object.
     * @return A string representing the user's password.
     */
    public String getPassword() {
        return password;
    }
    /**
     * Returns the name field value of the UserInfo object.
     *
     * @return A string representing the user's name.
     */

    public String getName() {
        return name;
    }
}