/**
 The BlogRepository interface represents a repository for blog objects, which are persisted in a relational database.
 This interface extends the JpaRepository interface, which provides CRUD (Create, Read, Update, Delete) operations
 for entities in a database. It defines a search method that uses JPQL (Java Persistence Query Language) to search
 for clients that match a given keyword.
 @Query annotation is used to define a JPQL query that searches for posts of the blog that match a given keyword.
 This interface is a crucial part of the Spring Data JPA framework, and it is responsible for providing an abstraction
 layer between the application and the database.
 @author Matthew Suprunov
 @version 1.0
 @since 20/04/2023
 */

package com.example.blog;

import java.util.List;

import com.example.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query; // JPQL is just an object-oriented way of defining queries based on entity attributes.
// JPQL uses the entity object model instead of database tables to define a query

public interface BlogRepository extends JpaRepository<Blog, Long> {
    /**
     * Searches for posts by id that match a given keyword using JPQL.
     * @param keyword A string that represents the keyword to search for.
     * @return A list of posts that match the given keyword.
     */
    @Query("select p from Blog p where concat('',p.post_id) like %?1%") // the concatted method parameter will be assigned to the query parameter with index 1.
    List<Blog> searchPostById(String keyword);
    /**
     * Searches for posts by their names that match a given keyword using JPQL.
     * @param keyword A string that represents the keyword to search for.
     * @return A list of posts that match the given keyword.
     */
    @Query("SELECT p FROM Blog p WHERE p.post_name LIKE %?1%")
    List<Blog> searchPostByName(String keyword);
    /**
     * Searches for posts by the date they were published that match a given keyword using JPQL.
     * @param keyword A string that represents the keyword to search for.
     * @return A list of posts that match the given keyword.
     */
    @Query("SELECT p FROM Blog p WHERE p.publish_date LIKE %?1%")
    List<Blog> searchPostByDate(String keyword);
    /**
     * Searches for posts by text that match a given keyword using JPQL.
     * @param keyword A string that represents the keyword to search for.
     * @return A list of posts that match the given keyword.
     */
    @Query("SELECT p FROM Blog p WHERE p.text LIKE %?1%")
    List<Blog> searchPostByText(String keyword);
    /**
     * Searches for posts by a person who wrote them that match a given keyword using JPQL.
     * @param keyword A string that represents the keyword to search for.
     * @return A list of posts that match the given keyword.
     */
    @Query("SELECT p FROM Blog p WHERE p.client_name LIKE %?1%")
    List<Blog> searchPostByClient_name(String keyword);
    /**
     * Searches for posts by all the parameters that match a given keyword using JPQL.
     * @param keyword A string that represents the keyword to search for.
     * @return A list of posts that match the given keyword.
     */
    @Query("select p from Blog p where concat(p.post_id, '', p.post_name, '', p.publish_date, '', p.text, '', p.client_name) like %?1%") // the concatted method parameter will be assigned to the query parameter with index 1.
    List<Blog> searchPostByKeyword(String keyword);
}
