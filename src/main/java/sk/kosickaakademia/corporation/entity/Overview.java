package sk.kosickaakademia.corporation.entity;

public class Overview {
    private int count;
    private int countMale;

    public int getCount() {
        return count;
    }

    public int getCountMale() {
        return countMale;
    }

    public int getCountFemale() {
        return countFemale;
    }

    public int getMinAge() {
        return minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public double getAvgAge() {
        return avgAge;
    }

    private int countFemale;
    private int minAge;
    private int maxAge;
    private double avgAge;
}
