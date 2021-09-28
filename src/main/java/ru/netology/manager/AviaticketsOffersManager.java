package ru.netology.manager;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netology.domain.AviaticketsOffers;
import ru.netology.repository.AviaticketsOffersRepository;

import java.util.Arrays;

@Data
@NoArgsConstructor
public class AviaticketsOffersManager {
    private AviaticketsOffersRepository repository;

    public AviaticketsOffersManager(AviaticketsOffersRepository repository) {
        this.repository = repository;
    }

    public void add(AviaticketsOffers item) {
        repository.save(item);
    }

    public AviaticketsOffers[] findAll(String departureAirport, String arrivalAirport) {
        AviaticketsOffers[] result = new AviaticketsOffers[0];
        for (AviaticketsOffers item : repository.findAll()) {
            if (item.getDepartureAirport().contains(departureAirport) && item.getArrivalAirport().contains(arrivalAirport)) {
                AviaticketsOffers[] tmp = new AviaticketsOffers[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = item;
                result = tmp;
            }
        }
        Arrays.sort(result);
        return result;
    }
}
