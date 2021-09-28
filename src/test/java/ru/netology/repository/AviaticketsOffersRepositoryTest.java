package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.AviaticketsOffers;

import static org.junit.jupiter.api.Assertions.*;

public class AviaticketsOffersRepositoryTest {
    private AviaticketsOffersRepository repository = new AviaticketsOffersRepository();
    private AviaticketsOffers first = new AviaticketsOffers(1, 30000, "DME", "AYT", 200);
    private AviaticketsOffers second = new AviaticketsOffers(2, 28000, "DME", "AYT", 200);
    private AviaticketsOffers third = new AviaticketsOffers(3, 32000, "VKO", "AYT", 200);

    @BeforeEach
    public void setUp() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
    }

    @Test
    public void shouldFindAllTickets() {
        AviaticketsOffers[] expected = new AviaticketsOffers[]{first, second, third};
        AviaticketsOffers[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindByIdIfExist() {
        int idToFind = 3;
        AviaticketsOffers expected = third;
        AviaticketsOffers actual = repository.findById(idToFind);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindByIdIfNotExist() {
        int idToFind = 4;
        AviaticketsOffers expected = null;
        AviaticketsOffers actual = repository.findById(idToFind);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldRemoveByIdIfExist() {
        int removeId = 2;
        repository.removeById(removeId);
        AviaticketsOffers[] expected = new AviaticketsOffers[]{first, third};
        AviaticketsOffers[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotRemoveByIdIfNotExist() {
        int removeId = 4;
        assertThrows(NotFoundException.class, () -> repository.removeById(removeId));
    }
}