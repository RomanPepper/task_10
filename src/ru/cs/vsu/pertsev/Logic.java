package ru.cs.vsu.pertsev;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Logic {
    public AnswerTransmitter solution(List<Candy> inputCandyList, Integer moneys) {
        int k = getMaximumPossibleKG(inputCandyList, moneys);
        List<Candy> finalCandyList = new ArrayList<>();
        Collections.sort(inputCandyList);
        Collections.reverse(inputCandyList); //Список конфет по цене от большего к меньшему

        int i = inputCandyList.size() - k;
        while(k > 0) {
            if(moneys - inputCandyList.get(i).getPrice() >= 0) {
                finalCandyList.add(inputCandyList.get(i));
                moneys -= inputCandyList.get(i).getPrice();
            }
            i--;
            k--;
        }

        return new AnswerTransmitter(finalCandyList, moneys);

    }

    public boolean isCandyNotInList(Candy candy, List<Candy> candyList) {
        for(Candy suppCandy: candyList) {
            if(suppCandy == candy) {
                return false;
            }
        }
        return true;
    }

    public int getMaximumPossibleKG(List<Candy> candyList, Integer moneys) {
        candyList.sort(Collections.reverseOrder());

        int count = 0;
        for(Candy candy: candyList) {
            if(moneys - candy.getPrice() >= 0) {
                count += 1;
                moneys -= candy.getPrice();
            }
        }
        return count;
    }
}
