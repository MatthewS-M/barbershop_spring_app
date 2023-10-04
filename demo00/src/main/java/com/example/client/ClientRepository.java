/**
 The ClientRepository interface represents a repository for Client objects, which are persisted in a relational database.
 This interface extends the JpaRepository interface, which provides CRUD (Create, Read, Update, Delete) operations
 for entities in a database. It defines a search method that uses JPQL (Java Persistence Query Language) to search
 for clients that match a given keyword.
 @Query annotation is used to define a JPQL query that searches for clients that match a given keyword.
 The query searches for clients where the concatenation of their ID, full name, visit date,
 service, and master name matches the keyword.
 This interface is a crucial part of the Spring Data JPA framework, and it is responsible for providing an abstraction
 layer between the application and the database.
 @author Matthew Suprunov
 @version 1.0
 @since 20/04/2023
 */

package com.example.client;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query; // JPQL is just an object-oriented way of defining queries based on entity attributes.
// JPQL uses the entity object model instead of database tables to define a query

public interface ClientRepository extends JpaRepository<Client, Long> {
    /**
     * Searches for clients that match a given keyword using JPQL.
     * @param keyword A string that represents the keyword to search for.
     * @return A list of clients that match the given keyword.
     */
    @Query("select p from Client p where concat(p.id, '', p.full_name, '', p.visit_date, '', p.service, '', p.master_name) like %?1%") // the concatted method parameter will be assigned to the query parameter with index 1.
    List<Client> search(String keyword);
}
