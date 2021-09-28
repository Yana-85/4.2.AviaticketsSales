package ru.netology.repository;

import ru.netology.domain.AviaticketsOffers;

public class AviaticketsOffersRepository {
    private AviaticketsOffers[] items = new AviaticketsOffers[0];

    public void save(AviaticketsOffers item) {
        int length = items.length + 1;
        AviaticketsOffers[] tmp = new AviaticketsOffers[length];
        System.arraycopy(items, 0, tmp, 0, items.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = item;
        items = tmp;
    }

    public AviaticketsOffers[] findAll() {
        return items;
    }

    public AviaticketsOffers findById(int id) {
        for (AviaticketsOffers item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void removeById(int id) {
        if (findById(id) == null) {
            throw new NotFoundException("Element with id: " + id + " not found");
        }
        int length = items.length - 1;
        AviaticketsOffers[] tmp = new AviaticketsOffers[length];
        int index = 0;
        for (AviaticketsOffers item : items) {
            if (item.getId() != id) {
                tmp[index] = item;
                index++;
            }
        }
        items = tmp;
    }
}
