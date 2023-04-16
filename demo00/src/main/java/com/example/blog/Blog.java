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

    public Blog() {
    }

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

    @Override
    public String toString() {
        return "post [post_id=" + post_id + ", post_name=" + post_name + ", publish_date=" + publish_date + ", text=" + text + ", client_name=" + client_name + "]";
    }
}








