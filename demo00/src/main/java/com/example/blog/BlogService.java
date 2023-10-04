/**
 The BlogService class holds the business logic of managing blogs.
 Manages blogs in the system. It provides methods for searching, creating, retrieving, and deleting clients.
 @Service indicates that this class belongs to the service layer and can be injected as a dependency into other components.
 The class provides methods for listing all clients, searching for clients by keyword,
 saving, retrieving by id and deleting posts.
 It has a dependency on the BlogRepository interface and uses its methods to interact with the database.
 The listAll method returns a list of all posts, and the search method returns a list of posts matching a keyword.
 */

package com.example.blog;

// в этом классе хранится бизнес-логика
import java.util.List; // ordered collection (sequence) односоставная. the elements of the list are of the same data type.
// Each element has an index and can be manipulated based on their position.
// The sequential nature of List allows the use of iteration methods (listIterator).
import com.example.client.Client;
import com.example.client.ClientRepository;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired; // для связи зависимостей из всех классов.
// Tells the application context to inject an instance of CarRepository here
import org.springframework.stereotype.Service; // аннотация для обнаружения всех зависимостей, указывает, что класс CarService принадлежит серверу SpringBoot
// We specify a class with @Service to indicate that they’re holding the business logic.
// Besides being used in the service layer, there isn’t any other special use for this annotation.
// The utility classes can be marked as Service classes.
@Service
public class BlogService {
    @Autowired
    private BlogRepository repo;

    private String postKeywordName;
    private String postKeyword;

    /**
     * Retrieves keyword name of the post
     * @return A String value that depicts keyword name of the post
     */
    public String getPostKeywordName() {
        return postKeywordName;
    }
    /**
     * Retrieves keyword of the post
     * @return A String value that depicts keyword of the post
     */
    public String getPostKeyword() {
        return postKeyword;
    }

    /**
     * Saves a new or updates an existing post to the system.
     @param blog The post to save.
     */
    public void save(Blog blog) {
        repo.save(blog);
    }
    /**
     Retrieves a post by its ID.
     @param id The ID of the post to retrieve.
     @return The post with the given ID, or null if not found.
     */
    public Blog get(Long id) {
        return repo.findById(id).get();
    }
    /**
     Deletes a post by its ID.
     @param id The ID of the post to delete.
     */
    public void delete(Long id) {
        repo.deleteById(id);
    }
    /**
     * This public method retrieves from BlogRepository the posts that match with the given keywords.
     * @param keywordId A String type parameter that defines a post_id to search by.
     * @param keywordPostName A String type parameter that defines a post_name to search by.
     * @param keywordDate A String type parameter that defines a publish_date to search by.
     * @param keywordText A String type parameter that defines a text to search by.
     * @param keywordClientName A String type parameter that defines a client_name to search by.
     * @param keyword A String type parameter that defines a general keyword to search by all fields.
     * @return List of Posts that were found based on the given criteria (keywords)
     */
    public List<Blog> listByPostCriteria(String keywordId, String keywordPostName,
                                       String keywordDate, String keywordText,
                                       String keywordClientName, String keyword) {
        if (!StringUtil.isNullOrEmpty(keywordId)) {
            this.postKeywordName = "keywordId";
            this.postKeyword = keywordId;
            return repo.searchPostById(keywordId);
        }
        else if (!StringUtil.isNullOrEmpty(keywordPostName)) {
            this.postKeywordName = "keywordPostName";
            this.postKeyword = keywordPostName;
            return repo.searchPostByName(keywordPostName);
        }
        else if (!StringUtil.isNullOrEmpty(keywordDate)){
            this.postKeywordName = "keywordDate";
            this.postKeyword = keywordDate;
            return repo.searchPostByDate(keywordDate);
        }
        else if (!StringUtil.isNullOrEmpty(keywordText)){
            this.postKeywordName = "keywordText";
            this.postKeyword = keywordText;
            return repo.searchPostByText(keywordText);
        }
        else if (!StringUtil.isNullOrEmpty(keywordClientName)){
            this.postKeywordName = "keywordClientName";
            this.postKeyword = keywordClientName;
            return repo.searchPostByClient_name(keywordClientName);
        }
        else if (!StringUtil.isNullOrEmpty(keyword)) {
            this.postKeywordName = "keyword";
            this.postKeyword = keyword;
            return repo.searchPostByKeyword(keyword);
        }
        else return repo.findAll();
    }
}
