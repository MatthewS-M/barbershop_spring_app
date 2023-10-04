/**
 The ClientService class holds the business logic of the application related to clients.
 Manages clients in the system. It provides methods for searching, creating, retrieving, and deleting clients.
 @Service indicates that this class belongs to the service layer and can be injected as a dependency into other components.
 The class provides methods for listing all clients, searching for clients by keyword,
 saving, retrieving by id and deleting clients.
 It has a dependency on the ClientRepository interface and uses its methods to interact with the database.
 The listAll method returns a list of all clients, and the search method returns a list of clients matching a keyword.
 */

package com.example.client;
import java.util.List; // ordered collection (sequence). The elements of the list are of the same data type.
// Each element has an index and can be manipulated based on their position.
// The sequential nature of List allows the use of iteration methods (listIterator).
import org.springframework.beans.factory.annotation.Autowired; // для связи зависимостей из всех классов.
// Tells the application context to inject an instance of CarRepository here
import org.springframework.stereotype.Service; // аннотация для обнаружения всех зависимостей, указывает, что класс CarService принадлежит серверу SpringBoot
// We specify a class with @Service to indicate that they’re holding the business logic.
// Besides being used in the service layer, there isn’t any other special use for this annotation.
// The utility classes can be marked as Service classes.
@Service
public class ClientService {
    @Autowired
    private ClientRepository repo;
    /**
     Returns a list of all clients in the system.
     @param keyword A keyword to filter the results by. If null, returns all clients.
     @return A list of clients matching the search criteria.
     */
    public List<Client> listAll(String keyword) { // коллекция и метод, отвечающий за поиск и фильтр в нашей системе
        if (keyword!=null) {
            return repo.search(keyword);
        }
        return repo.findAll();
    }

    /**
     * Saves a new or updates an existing client to the system.
     @param client The client to save.
     */
    public void save(Client client) {
        repo.save(client);
    }
    /**
     Retrieves a client by its ID.
     @param id The ID of the client to retrieve.
     @return The client with the given ID, or null if not found.
     */
    public Client get(Long id) {
        return repo.findById(id).get();
    }
    /**
     Deletes a client by its ID.
     @param id The ID of the client to delete.
     */
    public void delete(Long id) {
        repo.deleteById(id);
    }

}
