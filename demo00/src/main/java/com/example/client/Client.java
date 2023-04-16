package com.example.client;
// Object Relational Mapping (ORM) is concept/process of converting the data from Object oriented language to relational DB and vice versa
// Hibernate: Its the implementation of above concept.
import jakarta.persistence.*;
// By marking the @Id field with @GeneratedValue we are now enabling id generation.
// Which means that the persistence layer will generate an Id value for us and handle the auto incrementing

@Entity
@Table(name = "client")// позволяет Джава-класс представлять, как объект базы данных. defines that a class can be mapped to a table.
public class Client {
    private Long id;
    private String full_name;
    private String visit_date;
    private String service;
    private String master_name;

    protected Client() {
    }

    @Id // indicating the member field below is the primary key of current entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // to configure the way of increment of the specified column(field). you may specify auto_increment in the definition of table to make it self-incremental, and then use the following
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getVisit_date() {
        return visit_date;
    }

    public void setVisit_date(String visit_date) {
        this.visit_date = visit_date;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getMaster_name() {
        return master_name;
    }

    public void setMaster_name(String master_name) {
        this.master_name = master_name;
    }

    @Override
    public String toString() {
        return "performance [id=" + id + ", full name=" + full_name + ", visit_date=" + visit_date + ", service=" + service + ", master=" + master_name + "]";
    }
}







