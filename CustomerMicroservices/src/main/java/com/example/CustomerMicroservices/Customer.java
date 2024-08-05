package com.example.CustomerMicroservices;

public class Customer {

    private Long id;
    private String name;
    private String password;
    private String email;

    public Customer(Long id, String name, String password, String email){
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }

    public void setName(){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setPassword(){
        this.password = password;
    }

    public String getPassword(){
        return this.password;
    }

    public void setEmail(){
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }
}
