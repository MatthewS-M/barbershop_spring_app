package com.example.blog;

import java.util.List;

import com.example.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query; // JPQL is just an object-oriented way of defining queries based on entity attributes.
// JPQL uses the entity object model instead of database tables to define a query

public interface BlogRepository extends JpaRepository<Blog, Long> {
    @Query("select p from Blog p where concat(p.post_id, '', p.post_name, '', p.publish_date, '', p.text, '', p.client_name) like %?1%") // the concatted method parameter will be assigned to the query parameter with index 1.
    List<Blog> search(String keyword);
}
