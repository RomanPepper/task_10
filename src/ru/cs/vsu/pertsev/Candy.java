package ru.cs.vsu.pertsev;

public class Candy implements Comparable<Candy>{
    private String name;
    private Integer price;

    public Candy(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public String toString(Candy candy) {
        return candy.getName();
    }

    @Override
    public int compareTo(Candy o) {
        return o.getPrice() - this.getPrice();
    }
}
