package com.example.client;
// в этом классе хранится бизнес-логика
import java.util.List; // ordered collection (sequence) односоставная. the elements of the list are of the same data type.
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
    public List<Client> listAll(String keyword) { // коллекция и метод, отвечающий за поиск и фильтр в нашей системе
        if (keyword!=null) { // если keyword
            return repo.search(keyword); // выводим все результаты поиска,  передача всех объектов из нашего репозитория
        }
        return repo.findAll(); // ничего не выведется
    }
    public void save(Client client) {
        repo.save(client);
    }
    public Client get(Long id) {
        return repo.findById(id).get();
    }
    public void delete(Long id) {
        repo.deleteById(id);
    }

}
