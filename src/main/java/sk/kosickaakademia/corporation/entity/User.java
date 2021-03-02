package sk.kosickaakademia.corporation.entity;

public class User {
    private int id;

    public User(int id, String fname, String lname, int age, boolean gender) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.age = age;
        this.gender = gender;
    }

    public User(String fname, String lname, int age, boolean gender) {
        this.fname = fname;
        this.lname = lname;
        this.age = age;
        this.gender = gender;
    }

    private String fname;
    private String lname;
    private int age;
    private boolean gender;

}
