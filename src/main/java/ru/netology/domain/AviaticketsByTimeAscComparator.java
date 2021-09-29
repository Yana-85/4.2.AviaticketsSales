package ru.netology.domain;

import java.util.Comparator;

public class AviaticketsByTimeAscComparator implements Comparator<AviaticketsOffers> {

    @Override
    public int compare(AviaticketsOffers o1, AviaticketsOffers o2) {
        return o1.getTravelTime() - o2.getTravelTime();
    }
}

