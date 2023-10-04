/**
 This interface defines a repository for UserInfo entities, using Spring Data JPA.
 It extends the JpaRepository interface, which provides basic CRUD operations for the entity.
 The repository is responsible for managing the persistence of UserInfo instances.
 It provides methods to find, save, delete, and update UserInfo entities in the database.
 The findByName method is a custom query method that retrieves the UserInfo instance by its name.
 It returns an Optional object that contains the entity if it exists, or an empty Optional if it doesn't.
 @author com.example.config
 @version 1.0
 @since 20/04/2023
 */
package com.example.config;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    /**
    Retrieves a UserInfo instance from the database by its name.
    @param username the name of the UserInfo to retrieve
    @return an Optional object containing the UserInfo, or an empty Optional if it doesn't exist
    */
    Optional<UserInfo> findByName(String username);
}