package model;

import java.time.LocalDate;

public abstract class Person {
    protected  String id;
    protected  String name;
    protected  String dateOfBirth;

    // Constructor for Person class
    public Person(String id, String name, String dateOfBirth) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    //Abstract method
    public abstract String getRole();

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public int getAge() {
        return java.time.Period.between(LocalDate.parse(dateOfBirth), LocalDate.now()).getYears();
    }

    public String toString() {
        return String.format("ID: %s | Name: %s | DOB: %s",
                id, name, dateOfBirth);
    }
}
