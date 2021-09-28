package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.AviaticketsOffers;
import ru.netology.repository.AviaticketsOffersRepository;

import static org.junit.jupiter.api.Assertions.*;

public class AviaticketsOffersManagerTest {
    private AviaticketsOffersRepository repository = new AviaticketsOffersRepository();
    private AviaticketsOffersManager manager = new AviaticketsOffersManager(repository);
    private AviaticketsOffers first = new AviaticketsOffers(1, 30000, "DME", "AYT", 200);
    private AviaticketsOffers second = new AviaticketsOffers(2, 28000, "DME", "AYT", 200);
    private AviaticketsOffers third = new AviaticketsOffers(3, 32000, "VKO", "AYT", 200);

    @BeforeEach
    public void setUp() {
        manager = new AviaticketsOffersManager(repository);
    }

    @Test
    public void shouldReturnEmptyIfNoTickets() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        AviaticketsOffers[] expected = new AviaticketsOffers[]{};
        AviaticketsOffers[] actual = manager.findAll("KZN", "LED");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindTicketIfContained() {
        manager.add(first);
        AviaticketsOffers[] expected = new AviaticketsOffers[]{first};
        AviaticketsOffers[] actual = manager.findAll("DME", "AYT");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSortTicketsIfContained() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        AviaticketsOffers[] expected = new AviaticketsOffers[]{second, first};
        AviaticketsOffers[] actual = manager.findAll("DME", "AYT");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchTicketsOfDepartureWithoutArrivalAirport() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        AviaticketsOffers[] expected = new AviaticketsOffers[]{};
        AviaticketsOffers[] actual = manager.findAll("DME", "KZN");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchTicketsWithoutDepartureWithArrivalAirport() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        AviaticketsOffers[] expected = new AviaticketsOffers[]{};
        AviaticketsOffers[] actual = manager.findAll("SVO", "AYT");
        assertArrayEquals(expected, actual);
    }
}
