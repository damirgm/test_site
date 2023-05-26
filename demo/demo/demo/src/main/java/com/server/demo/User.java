package com.server.demo;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    
    private String login;
    
    private String password;

    public User() {}

    // getters and setters
}
