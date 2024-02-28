package com.example.Challenge;

public class Challenge {
     private int id;
     private String month;
     private String description;

    public Challenge(int id, String month, String description) { //generate Contructor
        this.id = id;
        this.month = month;
        this.description = description;
    }

    public int getId() {      //generate Getter and Setter
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
