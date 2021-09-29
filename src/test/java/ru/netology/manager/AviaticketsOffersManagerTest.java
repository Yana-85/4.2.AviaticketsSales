package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.AviaticketsByTimeAscComparator;
import ru.netology.domain.AviaticketsOffers;
import ru.netology.repository.AviaticketsOffersRepository;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class AviaticketsOffersManagerTest {
    private AviaticketsOffersRepository repository = new AviaticketsOffersRepository();
    private AviaticketsOffersManager manager = new AviaticketsOffersManager(repository);
    private AviaticketsOffers first = new AviaticketsOffers(1, 30000, "DME", "AYT", 230);
    private AviaticketsOffers second = new AviaticketsOffers(2, 28000, "DME", "AYT", 200);
    private AviaticketsOffers third = new AviaticketsOffers(3, 32000, "VKO", "AYT", 230);

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
        AviaticketsOffers[] actual = manager.findTicketsByPrice("KZN", "LED");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindTicketIfContained() {
        manager.add(first);
        AviaticketsOffers[] expected = new AviaticketsOffers[]{first};
        AviaticketsOffers[] actual = manager.findTicketsByPrice("DME", "AYT");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSortTicketsIfContained() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        AviaticketsOffers[] expected = new AviaticketsOffers[]{second, first};
        AviaticketsOffers[] actual = manager.findTicketsByPrice("DME", "AYT");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchTicketsOfDepartureWithoutArrivalAirport() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        AviaticketsOffers[] expected = new AviaticketsOffers[]{};
        AviaticketsOffers[] actual = manager.findTicketsByPrice("DME", "KZN");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchTicketsWithoutDepartureWithArrivalAirport() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        AviaticketsOffers[] expected = new AviaticketsOffers[]{};
        AviaticketsOffers[] actual = manager.findTicketsByPrice("SVO", "AYT");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSortTicketsByTravelTime() {
        manager.add(first);
        manager.add(second);
        Comparator<AviaticketsOffers> comparator = new AviaticketsByTimeAscComparator();
        AviaticketsOffers[] expected = new AviaticketsOffers[]{second, first};
        AviaticketsOffers[] actual = manager.findTicketsByTime("DME", "AYT", comparator);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldBeEmptyIfNoTickets() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        Comparator<AviaticketsOffers> comparator = new AviaticketsByTimeAscComparator();
        AviaticketsOffers[] expected = new AviaticketsOffers[]{};
        AviaticketsOffers[] actual = manager.findTicketsByTime("KZN", "LED", comparator);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnOneIfContained() {
        manager.add(first);
        manager.add(third);
        Comparator<AviaticketsOffers> comparator = new AviaticketsByTimeAscComparator();
        AviaticketsOffers[] expected = new AviaticketsOffers[]{first};
        AviaticketsOffers[] actual = manager.findTicketsByTime("DME", "AYT", comparator);
        assertArrayEquals(expected, actual);
    }
}
