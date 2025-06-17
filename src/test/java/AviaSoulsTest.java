import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

public class AviaSoulsTest {
    AviaSouls aviaSouls = new AviaSouls();
    Ticket ticket1 = new Ticket("Москва", "Санкт-Петербург", 9_999,
            9_45, 14_15);
    Ticket ticket2 = new Ticket("Москва", "Санкт-Петербург", 15_200,
            14_00, 22_00);
    Ticket ticket3 = new Ticket("Москва", "Казань", 19_150,
            6_15, 8_05);
    Ticket ticket4 = new Ticket("Москва", "Санкт-Петербург", 18_345,
            15_20, 16_40);
    Ticket ticket5 = new Ticket("Челябинск", "Санкт-Петербург", 6_700,
            21_40, 23_35);

    @Test
    public void TicketSort() {

        Ticket[] tickets = {ticket1, ticket2, ticket3, ticket4, ticket5};

        Arrays.sort(tickets);

        Ticket[] expected = {ticket5, ticket1, ticket2, ticket4, ticket3};

        Assertions.assertArrayEquals(expected, tickets);
    }

    @Test
    public void TicketSearchSamePrice() {
        Ticket ticket6 = new Ticket("Москва", "Казань", 19_150,
                6_15, 8_05);
        Ticket[] tickets = {ticket1, ticket2, ticket3, ticket4, ticket5, ticket6};
        Arrays.sort(tickets);

        Ticket[] expected = {ticket5, ticket1, ticket2, ticket4, ticket3, ticket6};

        Assertions.assertArrayEquals(expected, tickets);
    }

    @Test
    public void TicketSearch() {
        Ticket[] tickets = {ticket1, ticket2, ticket3, ticket4, ticket5};
        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);
        aviaSouls.add(ticket4);
        aviaSouls.add(ticket5);
        aviaSouls.findAll();

        Ticket[] aviaSoulsTickets = aviaSouls.search("Москва", "Санкт-Петербург");

        Ticket[] expected = {ticket1, ticket2, ticket4};
        Assertions.assertArrayEquals(expected, aviaSoulsTickets);

    }

    @Test
    public void SearchLowTime() {
        TicketTimeComparator TimeComparator = new TicketTimeComparator();
        Ticket ticket6 = new Ticket("Москва", "Казань", 19_150,
                6_15, 8_05);
        Ticket[] tickets = {ticket1, ticket2, ticket3, ticket4, ticket5, ticket6};
        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);
        aviaSouls.add(ticket4);
        aviaSouls.add(ticket5);
        aviaSouls.add(ticket6);

        Arrays.sort(tickets, TimeComparator);

        Ticket[] expected = {ticket4, ticket3, ticket6, ticket5, ticket1, ticket2};
        Assertions.assertArrayEquals(expected, tickets);
    }

    @Test
    public void SearchFromToAndTime() {
        TicketTimeComparator TimeComparator = new TicketTimeComparator();
        Ticket ticket6 = new Ticket("Москва", "Санкт-Петербург", 18_345,
                15_20, 16_40);
        Ticket[] tickets = {ticket1, ticket2, ticket3, ticket4, ticket5, ticket6};
        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);
        aviaSouls.add(ticket4);
        aviaSouls.add(ticket5);
        aviaSouls.add(ticket6);

        Ticket[] searchAndSortBy = aviaSouls.searchAndSortBy("Москва", "Санкт-Петербург", TimeComparator);

        Ticket[] expected = {ticket4, ticket6, ticket1, ticket2};
        Assertions.assertArrayEquals(expected, searchAndSortBy);
    }

    @Test
    public void SearchPriceTrue() {
        Ticket ticket6 = new Ticket("Москва", "Санкт-Петербург", 18_345,
                15_20, 16_40);
        Ticket[] tickets = {ticket4, ticket6};

        boolean[] results = {
                ticket6.equals(ticket6),
                ticket4.equals(ticket6)
        };

        boolean[] expectedResults = {true, true};

        Assertions.assertArrayEquals(expectedResults, results);
    }
    @Test
    public void SearchPriceFalse() {
        Ticket ticket6 = new Ticket("Москва", "Санкт-Петербург", 14_345,
                15_20, 16_40);
        Ticket ticket7 = new Ticket("Москва", "Санкт-Петербург", 18_345,
                15_30, 16_40);
        Ticket ticket8 = new Ticket("Москва", "Санкт-Петербург", 18_345,
                15_20, 17_40);
        Ticket ticket9 = new Ticket("Москва", "Чебоксары", 18_345,
                15_20, 16_40);
        Ticket ticket10 = new Ticket("Чебоксары", "Санкт-Петербург", 18_345,
                15_20, 16_40);
        Ticket[] tickets = {ticket4, ticket6, ticket7, ticket8, ticket9, ticket10};
        String string = new String("Bla");

        boolean[] result = {
                ticket4.equals(ticket6),
                ticket4.equals(string),
                ticket4.equals(null),
                ticket4.equals(ticket7),
                ticket4.equals(ticket8),
                ticket4.equals(ticket9),
                ticket4.equals(ticket10)
        };
        boolean[] expectedResult = {
                false,
                false,
                false,
                false,
                false,
                false,
                false,
        };

        Assertions.assertArrayEquals(expectedResult, result);
    }
}
