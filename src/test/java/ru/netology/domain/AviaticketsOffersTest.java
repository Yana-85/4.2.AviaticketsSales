package ru.netology.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class AviaticketsOffersTest {
    private AviaticketsOffers first = new AviaticketsOffers(1, 30000, "DME", "AYT", 200);
    private AviaticketsOffers second = new AviaticketsOffers(2, 28000, "SVO", "AYT", 200);
    private AviaticketsOffers third = new AviaticketsOffers(3, 32000, "VKO", "AYT", 200);

    @Test
    public void shouldSortTicketsByPrice() {
        AviaticketsOffers[] expected = new AviaticketsOffers[]{second, first, third};
        AviaticketsOffers[] actual = new AviaticketsOffers[]{first, second, third};

        Arrays.sort(actual);

        assertArrayEquals(expected, actual);
    }
}
