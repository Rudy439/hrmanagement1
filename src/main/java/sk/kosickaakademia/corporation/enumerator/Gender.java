package sk.kosickaakademia.corporation.enumerator;

import sk.kosickaakademia.corporation.entity.User;

import java.util.List;

public enum Gender {
    MALE(0), FEMALE(1), OTHER(2);

    public int getValue() {
        return value;
    }

    private int value;
    Gender(int value){
        this.value = value;
    }

}
