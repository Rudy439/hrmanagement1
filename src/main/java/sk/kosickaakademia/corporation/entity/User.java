package sk.kosickaakademia.corporation.entity;

import sk.kosickaakademia.corporation.enumerator.Gender;

public class User {
    private int id;

    public User(int id, String fname, String lname, int age, int gender) {
        this (fname, lname, age, gender); //naraz volam konstruktor so styrmi parametrami
        this.id = id;
    }

    public User(String fname, String lname, int age, int gender) {
        this.fname = fname;
        this.lname = lname;
        this.age = age;
        this.gender = gender ==0?Gender.MALE : gender==1 ? Gender.FEMALE :Gender.OTHER;
    }

    private String fname;

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    private String lname;
    private int age;
    private Gender gender;

}
