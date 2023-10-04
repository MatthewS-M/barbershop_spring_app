/**
 The Blog class is a configuration class that represents information about blog in the system.
 @Entity annotation is used to indicate that this class represents a table in a database.
 @Data is a convenient annotation that generates all the boilerplate code required for getters and setters methods.
 @AllArgsConstructor and
 @NoArgsConstructor annotations are used to generate constructors with and without arguments respectively.
 The class has five instance variables, including post_id, post_name, publish_date, text and client name.
 @Id
 @GeneratedValue annotations, indicating that post_id is the primary key of the table and
 its value will be generated automatically. The post_name and publish_date fields store
 the information about the person who hava written a post and date when post was published.
 Text field represents the text client wrote in the system.
 This table ("blog") is wired with table "client" by foreign key (client_name). It means that each user who visited our barbershop
 leaves a post, that includes a review or personal recommendation.
 The class provides setter and getter methods that return each column in database respectively.

 @author Matthew Suprunov
 @version 1.0
 @since 20/04/2023
 */

package com.example.blog;

// Object Relational Mapping (ORM) is concept/process of converting the data from Object oriented language to relational DB and vice versa
// Hibernate: Its the implementation of above concept.
import jakarta.persistence.*;
// By marking the @Id field with @GeneratedValue we are now enabling id generation.
// Which means that the persistence layer will generate an Id value for us and handle the auto incrementing

@Entity
@Table(name = "blog")// позволяет Джава-класс представлять, как объект базы данных. defines that a class can be mapped to a table.
public class Blog {
    private Long post_id;
    private String post_name;
    private String publish_date;
    private String text;
    private String client_name;
    private String vk_link;
    private String link;

    public String getVk_link() {
        return vk_link;
    }

    public void setVk_link(String vk_link) {
        this.vk_link = vk_link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Blog() {
    }

    /** Id and GeneratedValue annotations indicate that post_id is the primary key of the table and
     * @return a Long type id of the post
     */
    @Id // indicating the member field below is the primary key of current entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // to configure the way of increment of the specified column(field). you may specify auto_increment in the definition of table to make it self-incremental, and then use the following
    public Long getPost_id() {
        return post_id;
    }

    public void setPost_id(Long post_id) {
        this.post_id = post_id;
    }

    public String getPost_name() {
        return post_name;
    }

    public void setPost_name(String post_name) {
        this.post_name = post_name;
    }

    public String getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(String publish_date) {
        this.publish_date = publish_date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    /**
     * Is used to transform all the parameters of the post into string format
     * @return a string with all post properties: id, name, publish date, text and client name
     */
    @Override
    public String toString() {
        return "post [post_id=" + post_id + ", post_name=" + post_name + ", publish_date="
                + publish_date + ", text=" + text + ", client_name=" + client_name + "]";
    }
}








