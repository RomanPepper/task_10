package ru.cs.vsu.pertsev;

import java.util.List;

public class AnswerTransmitter {
    private List<Candy> candyList;
    private Integer moneys;

    public AnswerTransmitter(List<Candy> candy, Integer moneys) {
        this.candyList = candy;
        this.moneys = moneys;
    }

    public List<Candy> getCandyList() {
        return candyList;
    }

    public Integer getMoneys() {
        return moneys;

    }
}
