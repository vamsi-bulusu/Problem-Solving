package com.geektrust;

public class Person {
    private String name;
    private Gender gender;

    Person(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }

    String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
