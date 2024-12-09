package com.example.beedelacruzmijares_regularloan;

public class User {
    private String id;
    private String name;
    private String loanStatus;

    // Default constructor required for calls to DataSnapshot.getValue(User.class)
    public User() {
    }

    public User(String id, String name, String loanStatus) {
        this.id = id;
        this.name = name;
        this.loanStatus = loanStatus;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLoanStatus() {
        return loanStatus;
    }
}

